const form = document.getElementById("form");
const firstName = document.getElementById("firstName");
const lastName = document.getElementById("lastName");
const email = document.getElementById("email");
const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirmPassword");
const reString = new RegExp("[a-zA-Z]+");
const rePass = new RegExp("^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$");

form.addEventListener("submit", (e) => {
  e.preventDefault();
  mainValidateFunction();
});

const validatePassword = () => {
  let error = document.getElementById("errorPassword");
  if (password.value.trim() === "") {
    password.style.borderColor = "red";
    error.style.color = "red";
    error.textContent = "Cannot be blank";
  } else {
    if (rePass.test(password.value.trim())) {
      error.textContent = "";
      console.log("test passed");
    } else {
    error.textContent = "Please enter a valid password. It must be at least 8 characters and contain one letter and number.";
    error.style.color = "red";
    console.log("test failed");
    }
  }
};

const validateConfirmPassword = () => {
  let error = document.getElementById("errorConfirmPassword");
  if (confirmPassword.value.trim() === "") {
    confirmPassword.style.borderColor = "red";
    error.style.color = "red";
    error.textContent = "Cannot be blank";
    return;
  } else if (password.value.trim() === confirmPassword.value.trim()) {
    error.textContent = "";
    return;
  } else {
    error.textContent = "Passwords must match!";
    error.style.color = "red";
    return;
  }
};

const validateEmail = () => {

  let error = document.getElementById("errorEmail");
  if (document.getElementById("email") === "") {
    email.style.borderColor = "red";
    error.style.color = "red";
    error.textContent = "Cannot be blank";
  } else if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email.value.trim())) {
      error.textContent = "";
      console.log("test passed");
    } else {
    error.textContent = "Please enter a valid email";
    email.style.borderColor = "red";
    error.style.color = "red";
    console.log("test failed");
    }
  }
;

const validateFirstName = () => {
  let error = document.getElementById("errorFirstName");
  if (firstName.value.trim() === "") {
    error.textContent = "Cannot be blank";
    firstName.style.borderColor = "red";
    error.style.color = "red";
    
  } else {
    if (reString.test(document.getElementById("firstName").value.trim())) {
      error.textContent = "";
      console.log("test pass");
      
    } else {
      // Changing content and color of content
      error.textContent = "Please enter your first name";
      firstName.style.borderColor = "red";
      error.style.color = "red";
      console.log("test failed");
      
    }
  }
};

const validateLastName = () => {
  let error = document.getElementById("errorLastName");
  if (lastName.value.trim() === "") {
    error.textContent = "Cannot be blank";
    lastName.style.borderColor = "red";
    error.style.color = "red";
    
  } else {
    if (reString.test(document.getElementById("lastName").value.trim())) {
      error.textContent = "";
      console.log("test pass");
      
    } else {
      // Changing content and color of content
      error.textContent = "Please enter your last name";
      error.style.color = "red";
      lastName.style.borderColor = "red";
      console.log("test failed");
      
    }
  }
};

function mainValidateFunction() {
  validateFirstName();
  validateLastName();
  validateEmail();
  validatePassword();
  validateConfirmPassword();
}


