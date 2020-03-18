# Send data From ExpenseShare Android App To Google Sheet

Using this ExpenseShare app, you will send app data to Google Sheets.

# What we will do
* Create a Google Form for receiving data
* Import the ExpenseShare app 
* Investigate the spreadsheet to find the input hooks
* Post the user data from ExpenseShare app to the spreadsheet using Retrofit

# ExpenseShare Previews

<img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/1.png" width="300">        <img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/2.png" width="300"> 
<img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/3.png" width="300">         <img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/4.png" width="300">


<img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/5.png">



# How to Use

* Download/Clone this demo app
* Create a google form with the questions as follows

	- Items
  	- Amount
    - Name
    ------
    
    <img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/6.png">
    
    ---
    - Now click on the "<b>eye</b>" button in your Google Form
    ---
 
     <img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/7.png">
    
    ---
    
    
    <img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/8.png">
        
    ---
    
When the form preview opens, right click and select ‘view page source’. You can now see the HTML for the page. Use your browser search function (CMD+F) and search for “<form”

(1), this will get you to the html for your form. The action of the form is the url we will be posting to 
(2). You then need to look for the <input> tags that have an id starting with entry. each one of these is the ID for one of your spreadsheet columns 
(3).
This screenshot shows the finding of the "entry" ID

---
 
 <img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/9.png">
    
---

From this we have:

*  Spreadsheet url:
https://docs.google.com/forms/u/0/d/e/1FAIpQLScn7SkOPGgGA5Ajz0DIPmHhqt0WBVnKBXRl8pfQYvQG5j09jA/formResponse
* The id of the Items column: entry.1906421542  
* The id of the Amount column: entry.682150236
* The id of the Name column: entry.542264605

---

* Now Import the demo app in Android Studio
 - Head over to app\src\main\java\com\d3v3loper\expenseshare\<b>SpreadsheetWebService.java</b>"
 - Paste your URL ID, and Entry ID's
 
<img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/android.png">

---
   
   * Now run the app & write your items, amount, name and click on send button
   * Your data will be sent to Google form's response section which can be viewed in Google Spreadhsheet
   
---

* For transferring all data to spreadsheet
  -Click on Responses --> select response destination --> create a new spreadsheet 
  
<img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/10.png">

---




* Output in spreadsheet

 <img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/11.png">

---



* Note: You need to customize Google Sheet to get this type of look

 <img src="https://raw.githubusercontent.com/sahilsiddiqui/ExpenseShareScreenshots/master/5.png">

---

# Libraries Used In This App

- [CardView](https://developer.android.com/reference/android/support/v7/widget/CardView.html)
- [Retrofit2](https://github.com/square/retrofit)
- [MaterialStyledDialogs](https://github.com/javiersantos/MaterialStyledDialogs)
- [ConstraintLayout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html)


---





    
