var btnOpen = document.querySelector('.product_delete-btn');
var modal = document.querySelector('.modal-delete');
var iconClose = document.querySelector('.modal-delete-header i');
var btnClose = document.querySelector('.cancel-btn');

function toggleModal(e){
    console.log(e.target);
    modal.classList.toggle('hide');
}
btnOpen.addEventListener('click',toggleModal);
btnClose.addEventListener('click',toggleModal);
iconClose.addEventListener('click',toggleModal);
modal.addEventListener('click',function(e){
    if(e.target===e.currentTarget){
        toggleModal();
    }
});



