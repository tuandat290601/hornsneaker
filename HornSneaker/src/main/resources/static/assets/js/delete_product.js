var btnOpen = Array.from(document.getElementsByClassName('product_delete-btn'))
var modal = document.querySelector('.modal-delete');
var iconClose = document.querySelector('.modal-delete-header i');
var btnClose = document.querySelector('.cancel-btn');
var btnAcp = document.querySelector('.accept-btn');

function toggleModal(e) {
    console.log(e.target.value);
    modal.classList.toggle('hide');
}
btnOpen.forEach(btn => {
    const v = btn.value
    btn.addEventListener('click', e => {
        modal.dataset.target = v//e.target.value
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



