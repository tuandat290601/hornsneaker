document.addEventListener("DOMContentLoaded", ()=>{
    const login = document.querySelector("#login-form");
    const signup = document.querySelector("#signup-form");

    document.querySelector(".link-to-signup-form").addEventListener("click",e =>{
        e.preventDefault();
        login.classList.remove("show-form");
        signup.classList.add("show-form");
    })

    document.querySelector(".link-to-login-form").addEventListener("click",e =>{
        e.preventDefault();
        signup.classList.remove("show-form");
        login.classList.add("show-form");
    })
});