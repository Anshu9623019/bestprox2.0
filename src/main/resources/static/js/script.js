let currentTheme = getTheme();
// console.log(currentTheme);
document.addEventListener("DOMContentLoaded",()=>{
    changeTheme();
})

//to do 
function changeTheme(){

    changePageTheme(currentTheme,currentTheme);

    const changeThemeButton = document.querySelector("#theme_change_button");
    
    changeThemeButton.addEventListener("click",(event)=>{
        console.log("change theme button clicked");
        let oldTheme = currentTheme;
        if(currentTheme=="dark"){
            currentTheme = 'light';
        }else{
            currentTheme = 'dark'
        }

        changePageTheme(oldTheme,currentTheme);
    })
    
}

// set theme to local storage
function setTheme(theme){
    localStorage.setItem('theme',theme);
}

//get theme from localstorage
function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light"
}

//change page theme
function changePageTheme(oldTheme,theme){
    setTheme(theme);
    document.querySelector('html').classList.remove(oldTheme);
    document.querySelector('html').classList.add(theme);
    document.querySelector("#theme_change_button")
         .querySelector('span').textContent = (theme =='light'?'Light' : 'Dark');
}