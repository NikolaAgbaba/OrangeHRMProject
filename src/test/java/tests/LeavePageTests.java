package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LeavePage;
import pages.LoginPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeavePageTests extends BaseTest{
    private LeavePage leavePage;
    private HomePage homePage;
    private SimpleDateFormat dateFormat;

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        leavePage = new LeavePage(dateFormat, driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("Leave");
    }

    @Test
    public void checkLeavesFilteringByDate(){
        try {
            Assert.assertTrue(leavePage.filterLeavesByDate("2023-02-12", "2023-04-23"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void filterByTheLeaveStatus(){
        Assert.assertTrue(leavePage.checkLeavesBySelectedStatus("Pending Approval"));
    }

    @Test
    public void filterByTheEmployeeName(){
        Assert.assertTrue(leavePage.searchByEmployeeName());
    }
}
