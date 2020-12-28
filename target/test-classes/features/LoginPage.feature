## mvn verify -Dtestsuite="googleTestSuite" -Dcucumber.options="src/test/resources/features" -Dparallel.tests=1
## mvn clean verify -Dtestsuite="LoginTestSuite" -Dcucumber.options="src/test/resources/features" -Dparallel.tests=1
# mvn verify -Dtestsuite="googleTestSuite" -Dcucumber.options="src/test/resources/features/test.feature" -Dcucumber.options="--tags @ --tags @ --tags @TC" -Dparallel.tests=1
@feature=Login
Feature: Login to website

  @TC1
  Scenario: Case 1: Login successfully with email and password
    Given Actor Navigate to automationpractice login site
    When Login with automationtest1993@gmail.com and automationtest1993
#      | email                   | password     |
#      | ledinhcuong99@gmail.com | Test11011990 |
#    Then Should navigate to home page site
