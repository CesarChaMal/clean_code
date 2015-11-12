package clean.code.testablehtml;

import fitnesse.responders.run.SuiteResponder;
import fitnesse.wiki.*;

public class TestableHtml {
    // some methods

    public String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
        return new PageTestingSurrounder(pageData, includeSuiteSetup).surround();
    }

    private class PageTestingSurrounder {

        private PageData pageData;

        private StringBuilder stringBuilder;

        private boolean includeSuiteSetup;

        public PageTestingSurrounder(PageData pageData, boolean includeSuiteSetup) {
            this.pageData = pageData;
            this.includeSuiteSetup = includeSuiteSetup;
            this.stringBuilder = new StringBuilder();
        }

        public String surround() throws Exception {
            if (isTestPage()) {
                surroundWithSetupAndTeardown();
            }

            return pageData.getHtml();
        }

        private void surroundWithSetupAndTeardown() throws Exception {
            includeSetUps(pageData.getWikiPage());
            includeContent();
            includeTearDowns(pageData.getWikiPage());
            updatePageDataContent();
        }

        private void updatePageDataContent() {
            pageData.setContent(stringBuilder.toString());
        }

        private StringBuilder includeContent() throws Exception {
            return stringBuilder.append(pageData.getContent());
        }

        private void includeSetUps(WikiPage wikiPage) throws Exception {
            String setupStatement = "!include -setup .";
            if (includeSuiteSetup) {
                stringBuilder.append(includePage(wikiPage, setupStatement, SuiteResponder.SUITE_SETUP_NAME));
            }
            stringBuilder.append(includePage(wikiPage, setupStatement, "SetUp"));
        }

        private void includeTearDowns(WikiPage wikiPage) throws Exception {
            String tearDownStatement = "!include -teardown .";
            stringBuilder.append(includePage(wikiPage, tearDownStatement, "TearDown"));
            if (includeSuiteSetup) {
                stringBuilder.append(includePage(wikiPage, tearDownStatement, SuiteResponder.SUITE_TEARDOWN_NAME));
            }
        }

        private String includePage(WikiPage wikiPage, String setupStatement, String pageName) throws Exception {
            WikiPage page = PageCrawlerImpl.getInheritedPage(pageName, wikiPage);
            if (page != null) {
                WikiPagePath pagePath = wikiPage.getPageCrawler().getFullPath(page);
                String pagePathName = PathParser.render(pagePath);
                return setupStatement + pagePathName + "\n";
            }
            return "";
        }

        private boolean isTestPage() throws Exception {
            return pageData.hasAttribute("Test");
        }
    }
}