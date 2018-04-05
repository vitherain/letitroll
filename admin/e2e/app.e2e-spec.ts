import { AppPage } from './app.po';

describe('admin App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display Features heading', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Features');
  });
});
