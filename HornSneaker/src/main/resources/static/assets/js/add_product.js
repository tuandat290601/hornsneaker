submitForms = function () {
    const size = document.getElementById('size').value
    const stock = document.getElementById('stock').value
    fetch('/admin/product/addSize', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({size, stock})
    })
    .then(res => {
        if (res.status == 200) {            
            console.log('up sizes: ok');
            const entry = document.createElement('div');
            entry.className = 'sizeandStock'
            entry.id = 'entry-' + size
            entry.innerHTML = 
                `<p>Size: </p>
                <p id="form-size">${size}</p>
                <p>Số lượng: </p>
                <p id="form-stock">${stock}</p>
                <button type="button" value="${size}" onclick="deleteSize(this)" class="btn btn-danger add-delete-btn">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>`
            document.querySelector('#frmSizeandStock').append(entry);
        }
    })
    // document.getElementById("frmAddProduct").submit();
    // document.getElementById("frmSizeandStock").submit();
}
submitForms2 = function () {
    document.getElementById("frmAddProduct").submit();
}

function deleteSize(e) {
    const size = e.value
    const product_id = null
    fetch('/admin/product/deleteSize', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({product_id, size})
    })
    .then(res => {
        if (res.status == 200) {         
            const target = document.getElementById('entry-' + size)
            target.remove()
        }
    })
}