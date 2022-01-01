var btnOpen = document.querySelectorAll('.product_delete-btn');
var modal = document.querySelector('.modal-delete');
var iconClose = document.querySelector('.modal-delete-header i');
var btnClose = document.querySelector('.cancel-btn');
var btnAcp = document.querySelector('.accept-btn');
var targetProduct;

function toggleModal(e) {
    console.log(e.target.value);
    modal.classList.toggle('hide');
}
btnOpen.forEach(btn => {
    btn.addEventListener('click', e => {
        targetProduct = e.target.value;
        toggleModal(e);
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
    window.location = '/admin/product/delete/' + targetProduct
})



