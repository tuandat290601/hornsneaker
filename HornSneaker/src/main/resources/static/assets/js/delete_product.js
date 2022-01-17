var btnOpen = document.querySelectorAll('.product_delete-btn');
var modal = document.querySelector('.modal-delete');
var iconClose = document.querySelector('.modal-delete-header i');
var btnClose = document.querySelector('.cancel-btn');
var btnAcp = document.querySelector('.accept-btn');

function toggleModal(e, target) {
    console.log(e.target.value);
    modal.classList.toggle('hide');
    modal.dataset.target = target
}
btnOpen.forEach(btn => {
    btn.addEventListener('click', e => {
        toggleModal(e, e.target.value);
    });
})
btnClose.addEventListener('click', toggleModal);
iconClose.addEventListener('click', toggleModal);
modal.addEventListener('click', function (e) {
    if (e.target === e.currentTarget) {
        toggleModal();
    }
});
btnAcp.addEventListener('click', e => {
    window.location = '/admin/product/delete/' + modal.dataset.target
})



