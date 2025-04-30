package com.mri.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.SneakyThrows;

public class Accounting {
    private final Page page;
    private final String MenuIcon = "menu-component div.mri-menu div.main-menu-arrow-icon";
    private final String AccountingTab = "menu-component  .mri-menu #menus #main-menu #menu-level-one [menu-code='PAC']";
    private final String AccountReceivable ="menu-component #menu-level-0-body [mnopdesc='Accounts Receivable']" ;
    private final String ImmediateDemandsCreation="menu-component .inner-menu-item-list  [mnopdesc='Immediate Demand Creation']";
    private  final String InnerFrame="iframe#HznFormFrame";
    private final String TenantRef=".hzn-canvas #tnntRef_TextBox";
    private final String TaxPointDate=".hzn-canvas #dLedmTaxPointDate_TextBox";
    private final String Message=".hzn-canvas #message";
    private final String LeaseRef="#LedmLeasRef_TextBox";
    private final String GLAccount="#gridBlLedm  #DExpAccountRef_TextBox";
    private final String Major="#gridBlLedm  #LedmCdmjRef_TextBox";
    private final String Pro_forma="#gridBlLedm .hzn-checkbox-container";
    private final String Payment_Plan=".hzn-canvas #ledmPayPlanCode_TextBox";
    private final String Area=".hzn-canvas #ledmArea_TextBox";
    private final String FromDate=".hzn-canvas #ledmFrmDate_TextBox";
    private final String ToDate=".hzn-canvas #ledmToDate_TextBox";
    private final String Description=".hzn-canvas #ledmDesc_TextBox";
    private final String Measure=".hzn-canvas #ledmAreaUnit_TextBox";
    private final String Currency=".hzn-canvas #ledmContCurrCode_TextBox";
    private final String DueOn=".hzn-canvas #ledmDueDate_TextBox";
    private final String RentalRate=".hzn-canvas #ledmRentRate_TextBox";
    private final String DocumentCurrency=".hzn-canvas #ledmDocCurCode_TextBox";
    private final String Tax=".hzn-canvas #dLedmVatdRef_TextBox";
    private final String Net=".hzn-canvas #dLedmVatdRef_TextBox";
    private final String BillingAddr=".hzn-canvas #ledmBillAddrRef_TextBox";
    private final String EFTBankAccount=".hzn-canvas #ledmAcRef_TextBox";
    private final String TaxReq=".hzn-canvas #dLedmVatAmt_TextBox";
    private final String Comment=".hzn-canvas #ledmComment_TextBox";

    private  String MainFrame(int tabNumber) {
        return "iframe#hzn-tab-" + tabNumber;
    }
    public Accounting(Page page) {
        this.page = page;
    }
    public void clickMenuIcon() {
        page.click(MenuIcon);
    }
    public void clickAccounting() {
        page.click(AccountingTab);
    }
    public void clickAccountReceivable() {
        page.click(AccountReceivable);
    }
    public void clickImmediateDemandsCreation() {
        page.click(ImmediateDemandsCreation);
        //page.pause();
    }
    @SneakyThrows
    public void  NextBtn()  {

        Thread.sleep(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(".hzn-footer-container .hzn-button-bar [data-action='Next']").click();
    }
    public void fillTenantRef() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(TenantRef).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(TenantRef).pressSequentially("KMTENANT");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(TenantRef).press("Enter");
    }
    public void fillTaxPointDate() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(TaxPointDate).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(TaxPointDate).pressSequentially("23-05-2025");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(TaxPointDate).press("Enter");
    }

    public void Message(){
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Message).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Message).pressSequentially("Testing");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Message).press("Enter");
    }
    public void fillLeaseRef() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(LeaseRef).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(LeaseRef).type("KMLEAS3");
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(LeaseRef).press("Enter");
    }
    public void fillGLAccount() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(GLAccount).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(GLAccount).pressSequentially("TEST");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(GLAccount).press("Enter");
    }
    public void fillMajor() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Major).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Major).pressSequentially("test23");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Major).press("Enter");
    }
  /*  public void fillProForma() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Pro_forma).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Pro_forma).check();
    }*/
    public void fillPaymentPlan() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Payment_Plan).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Payment_Plan).pressSequentially("Payment Plan 1");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Payment_Plan).press("Enter");
    }

    public void fillFromDate() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(FromDate).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(FromDate).pressSequentially("15-05-2025");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(FromDate).press("Enter");
    }
    public void fillToDate() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(ToDate).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(ToDate).pressSequentially("23-05-2025");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(ToDate).press("Enter");
    }
    public void fillArea() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Area).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Area).pressSequentially("23");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Area).press("Enter");
    }
   /* public void fillDescription() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Description).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Description).pressSequentially("Testing");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Description).press("Enter");
    }*/

   /* public void FillDueOn(){
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(DueOn).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(DueOn).pressSequentially("23-05-2025");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(DueOn).press("Enter");
    }*/
    public void fillMeasure() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Measure).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Measure).pressSequentially("Last");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Measure).press("Enter");
    }
    public void RentalRate() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(RentalRate).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(RentalRate).pressSequentially("1000");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(RentalRate).press("Enter");
    }

   /* public void Net(){
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Net).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Net).pressSequentially("100");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Net).press("Enter");
    }*/
  /* public void BillingAddr() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(BillingAddr).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(BillingAddr).pressSequentially("00000002");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(BillingAddr).press("Enter");
    }*/
   /* public void fillTaxReq() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(TaxReq).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(TaxReq).pressSequentially("100");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(TaxReq).press("Enter");
    }*/
    public void fillComment() {
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Comment).scrollIntoViewIfNeeded();
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Comment).pressSequentially("Testing");
        page.setDefaultTimeout(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(Comment).press("Enter");
    }
    @SneakyThrows
    public void ClickSaveBtn(){
        Thread.sleep(6000);
        page.frameLocator(MainFrame(1)).frameLocator(InnerFrame).locator(".hzn-footer-container .hzn-button-bar [data-action='Save']").click();
    }

    @SneakyThrows
    public void ClickYes(){
        Thread.sleep(6000);
        page.locator(".mri-dialog__footer .mri-toolbar #hzn-button-modal-0-0").click();
    }

}
