const EditBtn = document.querySelector('.edit-btn'); 
const Modal = document.querySelector('.profile-modal');
const YesBtn = document.querySelector('#yes');
const NoBtn = document.querySelector('#no');
EditBtn.addEventListener('click', e => {
    e.preventDefault();
    Modal.style.display = "block";
});
NoBtn.addEventListener('click', e=> {
    e.preventDefault();
    Modal.style.display = "none";
})
