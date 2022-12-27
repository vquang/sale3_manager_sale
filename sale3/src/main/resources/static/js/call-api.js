let limit = 6;
function block(element) {
    document.querySelector(element).style.display = 'block';
}
function flex(element) {
    document.querySelector(element).style.display = 'flex';
}
// page
function callFromPage(e, page) {
    switch (e) {
        case 'first':
            page--;
            break;
        case 'next':
            page++;
            break;
        case 'last':
            page++;
            break;
    }
    return page;
}
// user
function callRegister() {
    let roles = document.getElementsByName('role');
    roles[1].checked = true;
    let username = document.querySelector('#username');
    let password = document.querySelector('#password');
    username.value = ""; password.value = "";
    document.querySelector('.btn-register').onclick = function () {
        let role = roles[0];
        if(role.checked === false) role = roles[1];
        let request = {
            username: username.value,
            password: password.value,
            role: role.value
        }
        callApi(registerApi, 'POST', request, handleRegister);
    }
}
function callUser() {
    block('.user');
    callApi(userApi, 'GET', '', handleUser);
}
// category
function callShowAllCategories(page) {
    callApi(showAllCategoryApi + "?page=" + page + "&limit=" + 100, 'GET', '', handleShowAllCategories);
}
function callShowAllCategoriesName(page) {
    callApi(showAllCategoryApi + "?page=" + page + "&limit=" + 100, 'GET', '', handleShowAllCategoriesName);
}
function callShowAllCategoriesAdmin(page) {
    none('.base-form');
    none('.delete-error');
    callApi(showAllCategoryApi + "?page=" + page + "&limit=" + limit, 'GET', '', handleShowAllCategoriesAdmin);
}
function callShowAllCategoriesAdminFromPage(e) {
    let page = document.querySelector('.a-now').textContent;
    page = callFromPage(e, page);
    callShowAllCategoriesAdmin(page);
}
function callCreateCategory() {
    let page = document.querySelector('.a-now').textContent;
    block('.base-form');
    block('.add-form');
    none('.update-form');
    none('.add-error');
    let name = document.querySelector('#adding-name');
    name.value = '';
    document.querySelector('.add-bt').onclick = function () {
        if(name.value === '') {
            block('.add-error');
        } else {
            let data = {
                name: name.value
            }
            callApi(categoryApi + "?page=" + page + "&limit=" + limit, 'POST', data, function () {
                callShowAllCategoriesAdmin(page);
            });
        }
    }
}
function callUpdateCategory(e) {
    let page = document.querySelector('.a-now').textContent;
    block('.base-form');
    block('.update-form');
    none('.add-form');
    none('.update-error');
    let id = e.classList[e.classList.length - 1];
    let name = document.querySelector('#updating-name');
    document.querySelector('#id').value = id;
    name.value = e.parentElement.parentElement.cells[1].innerHTML;
    document.querySelector('.update-bt').onclick = function () {
        if(name.value === '') {
            document.querySelector('.update-error').style.display = 'block';
        } else {
            let data = {
                name: name.value
            }
            callApi(categoryApi + "/" + id + "?page=" + page + "&limit=" + limit, 'PUT', data, function () {
                callShowAllCategoriesAdmin(page);
            });
        }
    }
}
function callDeleteCategory(e) {
    let page = document.querySelector('.a-now').textContent;
    let id = e.classList[e.classList.length - 1];
    let no = document.querySelector('.no');
    let yes = document.querySelector('.yes');
    yes.onclick = function () {
        callApi(categoryApi + "/" + id + "?page=" + page + "&limit=" + limit, 'DELETE', '', handleDeleteCategory)
    }
}
// product
function callShowAllProducts(id, page) {
    callApi(showAllProductApi + "/" + id + "?page=" + page + "&limit=" + limit, 'GET', '', handleShowAllProducts);
}
function callShowAllProductsAdmin(id, page) {
    none('.base-form');
    callApi(showAllProductApi + "/" + id + "?page=" + page + "&limit=" + limit, 'GET', '', handleShowAllProductsAdmin);
}
function callShowAllProductsFromPage(e) {
    let page = document.querySelector('.a-now').textContent;
    page = callFromPage(e, page);
    callShowAllProducts(document.querySelector('.category-now').id, page);
}
function callShowAllProductsAdminFromPage(e) {
    let page = document.querySelector('.a-now').textContent;
    page = callFromPage(e, page);
    callShowAllProductsAdmin(document.querySelector('.category-now').id, page);
}
function callShowProduct(id) {
    block('.cover');
    let page = document.querySelector('.a-now').textContent;
    callApi(productApi + "/" + id + "?page=" + page + "&limit=" + limit, 'GET', '', handleShowProduct);
}
function callCreateProduct() {
    let page = document.querySelector('.a-now').textContent;
    block('.base-form');
    block('.add-form');
    none('.update-form');
    none('.add-error');
    none('.product-detail');
    let id = document.querySelector('.category-now').id;
    let categoryId = document.querySelector('#adding-categoryId');
    categoryId.value = id;
    let name = document.querySelector('#adding-name');
    let image = document.querySelector('#adding-image');
    let cost = document.querySelector('#adding-cost');
    name.value = '';
    image.value = '';
    cost.value = '';
    document.querySelector('.add-bt').onclick = function () {
        if(name.value === '' || image.value === '' || cost.value === '') {
            let error = document.querySelector('.add-error');
            error.textContent = "Thiếu thông tin!";
            block('.add-error');
        } else if(isNaN(Number(cost.value))) {
            let error = document.querySelector('.add-error');
            error.textContent = "Giá tiền cần điền số nguyên!";
            block('.add-error');
        } else {
            let data = {
                name:name.value,
                image:image.value,
                cost:cost.value,
                categoryId:categoryId.value
            }
            callApi(productApi + "/" + id + "?page=" + page + "&limit=" + limit, 'POST', data, function () {
                callShowAllProductsAdmin(id, page);
            });
        }
    }
}
function callUpdateProduct(e) {
    let page = document.querySelector('.a-now').textContent;
    block('.base-form');
    block('.update-form');
    none('.add-form');
    none('.update-error');
    none('.product-detail');
    let id = e.classList[e.classList.length - 1];
    document.querySelector('#id').value = id;
    let row = e.parentElement.parentElement;
    let name = document.querySelector('#updating-name');
    let image = document.querySelector('#updating-image');
    let cost = document.querySelector('#updating-cost');
    name.value = row.cells[1].innerHTML;
    image.value = row.cells[2].innerHTML;
    cost.value = row.cells[3].innerHTML;
    document.querySelector('.update-bt').onclick = function () {
        if(name.value === '' || image.value === '' || cost.value === '') {
            let error = document.querySelector('.update-error');
            error.textContent = "Thiếu thông tin!";
            block('.update-error');
        }
        else if(isNaN(Number(cost.value))) {
            let error = document.querySelector('.update-error');
            error.textContent = "Giá tiền cần điền số nguyên!";
            block('.update-error');
        } else {
            let data = {
                name:name.value,
                image:image.value,
                cost:cost.value
            }
            callApi(productApi + "/" + id + "?page=" + page + "&limit=" + limit, 'PUT', data, function () {
                callShowAllProductsAdmin(document.querySelector('.category-now').id, page);
            });
        }
    }
}
function callDeleteProduct(e) {
    let page = document.querySelector('.a-now').textContent;
    let id = e.classList[e.classList.length - 1];
    let no = document.querySelector('.no');
    let yes = document.querySelector('.yes');
    yes.onclick = function () {
        callApi(productApi + "/" + id + "?page=" + page + "&limit=" + limit, 'DELETE', '', handleDeleteProduct)
    }
}
function callShowProductAdmin(id) {
    block('.base-form');
    none('.update-form');
    none('.add-form');
    block('.product-form');
    let page = document.querySelector('.a-now').textContent;
    callApi(productApi + "/" + id + "?page=" + page + "&limit=" + limit, 'GET', '', handleShowProductAdmin);
}
// item
function callAddItem(id) {
    let amount = document.querySelector('.amount');
    let page = document.querySelector('.a-now').textContent;
    let data = {
        amount: amount.textContent
    }
    callApi(itemApi + "/" + id + "?page=" + page + "&limit=" + limit, 'POST', data, function (){
        block('.add-item-success');
    });
}
function callDeleteItem(e) {
    let id = e.classList[e.classList.length - 1];
    let content = document.querySelector('.modal-body');
    let no = document.querySelector('.no');
    let yes = document.querySelector('.yes');
    content.textContent = 'Bạn có chắc chắn muốn xóa?';
    yes.onclick = function () {
        callApi(itemApi + "/" + id + "?page=1&limit=" + limit, 'DELETE', '', function () {
            callShowOrder();
        })
    }
}
function callUpdateItem(e, token) {
    if(token === 'plus') {
        e.children[1].firstChild.textContent++;
    }
    else if(token === 'minus' && e.children[1].firstChild.textContent !== '1') {
        e.children[1].firstChild.textContent--;
    }
    let id = e.classList[e.classList.length - 1];
    let data = {
        amount:e.children[1].firstChild.textContent
    }
    callApi(itemApi + "/" + id + "?page=1&limit=" + limit, 'PUT', data, callShowOrder);
}
function callShowItem(e) {
    block('.cover');
    let id = e.textContent;
    callApi(itemApi + "/" + id + "?page=1&limit=" + limit, 'GET', '', handleShowItem);
}
function callShowItemAdmin(e) {
    block('.base-form');
    flex('.item-detail');
    none('.item-list');
    let id = e.classList[e.classList.length - 1];
    let page = document.querySelector('.a-now').textContent;
    callApi(itemApi + "/" + id + "?page=" + page + "&limit=" + limit, 'GET', '', handleShowItemAdmin);
}
// order
function callShowOrder() {
    callApi(orderApi + "?page=1&limit=" + limit, 'GET', '', handleShowOrder);
}
function changeOrder(ask) {
    let content = document.querySelector('.modal-body');
    let no = document.querySelector('.no');
    let yes = document.querySelector('.yes');
    let order = document.querySelector('.order');
    let id = order.classList[order.classList.length - 1];
    if(ask === 'cancel') {
        content.textContent = 'Bạn có chắc chắn muốn hủy?';
        yes.onclick = function () {
            callApi(cancelOrderApi + "/" + id + "?page=1&limit=" + limit, 'GET', '', function () {
                block('.alert-cancel');
                none('.order');
            })
        }
    } else {
        content.textContent = 'Bạn có chắc chắn muốn thanh toán?';
        let address = document.querySelector("input[name='address']");
        yes.onclick = function () {
            if(address.value === '') {
                block('.alert-error');
            } else {
                let data = {
                    address:address.value
                }
                callApi(orderApi + "/" + id + "?page=1&limit=" + limit, 'POST', data, function () {
                    none('.alert-error');
                    block('.alert-success');
                    none('.order');
                })
            }
        }
    }
}

function callShowAllOrders(page) {
    none('.base-form');
    callApi(showAllOrderApi + "?page=" + page + "&limit=" + limit, 'GET', '', handleShowAllOrders);
}
function callShowAllOrdersFromPage(e) {
    let page = document.querySelector('.a-now').textContent;
    page = callFromPage(e, page);
    callShowAllOrders(page);
}
function callShowOrderAdmin(e) {
    block('.base-form');
    flex('.item-list');
    none('.item-detail');
    let id = e.classList[e.classList.length - 1];
    let page = document.querySelector('.a-now').textContent;
    callApi(orderApi + "/" + id + "?page=" + page + "&limit=" + limit, 'GET', '', handleShowOrderAdmin);
}












































