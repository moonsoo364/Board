const toggle= document.querySelector('.navbar_togle');
const menu= document.querySelector('.navbar_menu');
const reg= document.querySelector('.navbar_reg');

toggle.addEventListener('click',()=>{
    menu.classList.toggle('active');
    reg.classList.toggle('active');
})