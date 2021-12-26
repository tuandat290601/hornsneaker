var btnOpen = document.querySelector('.delete-btn')
var modal = document.querySelector('.modal-delete')
var iconClose = document.querySelector('.modal-header i')
var btnClose = document.querySelector('.modal-footer button')

function toggleModal(){
    console.log(this)
    modal.classList.toggle('hide')
}
btnOpen.addEventListener('click',toggleModal);



