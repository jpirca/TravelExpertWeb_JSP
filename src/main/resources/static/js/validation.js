//***************************************************************************//
//** Variables which will be used in functions below**//
//***************************************************************************//
var errorMessage = document.getElementById('errorMessageText');
var errorMessageBlock = document.getElementById('errorMessageBody');

//***************************************************************************//
// The main validation function checks:
//   1. If all required fields filled
//   2. Inside form validation function postal code validation triggers as a callback
//   3. If function in paragraph returns true value, users pop up dialog lunches
//***************************************************************************//

// function below
function validation(btnname, myForm) {
   var validFields = formValidation(myForm,validatePO);

   if (validFields==true){
     /*displayPopUp(btnname);*/
     $(document).ready(function(){
    //***************************************
        // $.post("http://localhost:8080/customers/insert_customer",
        // $("#regForm").serialize(),
        // function(data,status){
        //   alert("Data Inserted " + "\nStatus: " + status);
        // });
    //**************************
        $.ajax({
        url: "http://localhost:8080/customers/insert_customer",
        type: "POST",
        data: $("#regForm").serialize(),
        success: function(){
          window.location.replace("regconfirmation.html");
          //alert('success');
        },
        error: function(){
          alert('Sorry, something went wrong with our server');
        }
        });
    //****************************
    });
  }
 }
 //***************************************************************************//
 //** Function validates if all requred fields filled and then lunches PO validation**//
 //***************************************************************************//
function formValidation(myForm,callback) {

  var formValid = false;

  var fname = document.forms["regform"]["fname"].value;
  var lname = document.forms["regform"]["lname"].value;
  var email = document.forms["regform"]["email"].value;

  if (fname==null || fname==""){
      document.forms["regform"]["fname"].style.borderColor = "#ff1a1a";
      errorMessage.innerHTML = "Required field \"First Name\" is not filled";
      errorMessageBody.style.display = "block";
      formValid = false;
      return formValid;
  } else if (lname==null || lname=="") {
      document.forms["regform"]["lname"].style.borderColor = "#ff1a1a";
      errorMessage.innerHTML = "Required field \"Last Name\" is not filled";
      errorMessageBody.style.display = "block";
      formValid = false;
      return formValid;
  } else if (email==null || email=="") {
      document.forms["regform"]["email"].style.borderColor = "#ff1a1a";
      errorMessage.innerHTML = "Required field \"Email\" is not filled";
      errorMessageBody.style.display = "block";
      formValid = false;
      return formValid;
  } else{
      formValid = true;
  }

  if(formValid == true){
    var validPO = callback(myForm);
    return validPO;
  }

  console.log("form Valid - "+ formValid);
  console.log("PO valid - "+validPO );
}
//***************************************************************************//
//** Function to validate Postal Code  **//
//***************************************************************************//
function validatePO(myForm) {

  var reg = /^[A-Z]\d[A-Z]\d[A-Z]\d$/;
  if (!reg.test(myForm.postal.value)) {
    document.forms["regform"]["postal"].style.borderColor = "#ff1a1a";
    errorMessage.innerHTML = "The postal you entered is not valid";
    errorMessageBody.style.display = "block";
    return false;
  } else{
    return true;
   }
  }

//***************************************************************************//
//** Function to close Erroe Message **//
//***************************************************************************//
function closeErrorMessage() {
    errorMessageBody.style.display = "none";
}
