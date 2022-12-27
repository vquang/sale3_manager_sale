// page
function handlePage(page, totalPages) {
    let first = document.querySelector('.first');
    let now = document.querySelector('.now');
    let next = document.querySelector('.next');
    let last = document.querySelector('.last');
    document.querySelector('.a-now').innerHTML = page;
    document.querySelector('.a-next').innerHTML = page + 1;
    now.classList.add('active');
    // check
    if (page === 1) first.classList.add('disabled');
    else first.classList.remove('disabled');
    if (page >= totalPages) {
        next.classList.add('disabled');
        last.classList.add('disabled');
    } else {
        next.classList.remove('disabled');
        last.classList.remove('disabled');
    }
}

// amount item
function cal(e, token) {
    let amount = e.children[1].firstChild.textContent;
    if (token === 'plus') e.children[1].firstChild.textContent++;
    else if (token === 'minus' && amount !== '1') e.children[1].firstChild.textContent--;
    if (e.children[1].firstChild.textContent === '1') e.children[0].classList.add('disabled');
    else {
        e.children[0].classList.remove('disabled');
    }
}

// user
function handleRegister(response) {
    let status = response['status'];
    if (status['code'] === '111') {
        let error = document.querySelector('.error');
        error.style.display = 'block';
    } else {
        window.location = "http://localhost:8082/home";
    }
}

function handleUser(response) {
    let data = response['data'];
    let html = `
        <table>
            <tr>
                <th>username:</th>
                <td class="pl-2 text-info">${data['username']}</td>
            </tr>
            <tr class="mt-2">
                <th>quyền:</th>
                <td class="pl-2 text-info">${data['role']}</td>
            </tr>
        </table>
    `;
    document.querySelector('.user-body').innerHTML = html;
}

// category
function setCategory(id, name) {
    document.querySelector('.category-now').id = id;
    document.querySelector('.category-now').textContent = name;
}

function handleShowAllCategories(response) {
    let data = response['data'];
    let html = '';
    for (let index in data) {
        html += `
        <a class="dropdown-item" id="${data[index]['id']}"
        onclick="setCategory(this.id, this.textContent); callShowAllProducts(this.id, 1)">${data[index]['name']}</a>
        `;
    }
    document.querySelector('.category-name').innerHTML = html;
    setCategory(data[0]['id'], data[0]['name']);
    callShowAllProducts(data[0]['id'], 1);
}

function handleShowAllCategoriesName(response) {
    let data = response['data'];
    let html = '';
    for (let index in data) {
        html += `
        <a class="dropdown-item" id="${data[index]['id']}"
        onclick="setCategory(this.id, this.textContent); callShowAllProductsAdmin(this.id, 1)">${data[index]['name']}</a>
        `;
    }
    document.querySelector('.category-name').innerHTML = html;
    setCategory(data[0]['id'], data[0]['name']);
    callShowAllProductsAdmin(data[0]['id'], 1);
}

function handleShowAllCategoriesAdmin(response) {
    let data = response['data'];
    let tbody = '';
    for (let index in data) {
        tbody += `
            <tr>
                <td>${data[index]['id']}</td>
                <td>${data[index]['name']}</td>
                <td>${data[index]['createTime']}</td>
                <td>${data[index]['updateTime']}</td>
                <td>
                    <button type="button" class="btn btn-warning mr-2 ${data[index]['id']}" style="padding:3px 10px;"
                    onclick="callUpdateCategory(this)">
                    Sửa</button>
                    <button type="button" class="btn btn-danger ${data[index]['id']}" data-toggle="modal" data-target="#myModal" style="padding:3px 10px;"
                    onclick="callDeleteCategory(this)">
                    Xóa</button>
                </td>
            </tr>   
        `;
    }
    document.querySelector('tbody').innerHTML = tbody;
    if (data.length === 0) handlePage(1, 0);
    else handlePage(data[0]['page'], data[0]['totalPages']);
}

function handleDeleteCategory(response) {
    let data = response['data'];
    callShowAllCategoriesAdmin(data['page']);
    let status = response['status'];
    if (status['code'] === '111') block('.delete-error');
}

// product
function handleShowAllProducts(response) {
    let data = response['data'];
    let html = '';
    for (let index in data) {
        html += `
        <div style="background-color: white;width:16%; border-radius: 10%;">
            <div class="card">
                <div class="card-body">
                <img class="card-img-top" 
                src="/image/${data[index]['image']}" alt="Card image" style="width:100%; height:200px;">
                    <h4 class="card-title">${data[index]['name']}</h4>
                    <p class="card-text font-weight-bold"><code>Giá tiền: ${data[index]['cost']}</code></p>
                </div>
                <div class="card-footer">
                    <button type="button" class="btn btn-info ${data[index]['id']}" onclick="callShowProduct(this.classList[2])">Xem</button>
                </div>
            </div>
        </div>
        `;
    }
    document.querySelector('.product-body').innerHTML = html;
    if (data.length === 0) handlePage(1, 0);
    else handlePage(data[0]['page'], data[0]['totalPages']);
}

function handleShowAllProductsAdmin(response) {
    let data = response['data'];
    let html = '';
    for (let index in data) {
        html += `
        <tr>
                <td><kbd onclick="callShowProductAdmin(this.textContent)">${data[index]['id']}</kbd></td>
                <td>${data[index]['name']}</td>
                <td>${data[index]['image']}</td>
                <td>${data[index]['cost']}</td>
                <td>${data[index]['createTime']}</td>
                <td>${data[index]['updateTime']}</td>
                <td>
                    <button type="button" class="btn btn-warning mr-2 ${data[index]['id']}" style="padding:3px 10px;"
                    onclick="callUpdateProduct(this)">
                    Sửa</button>
                    <button type="button" class="btn btn-danger mr-2 ${data[index]['id']}" data-toggle="modal" data-target="#myModal" style="padding:3px 10px;"
                    onclick="callDeleteProduct(this)">
                    Xóa</button>
                </td>
            </tr>   
        `;
    }
    document.querySelector('tbody').innerHTML = html;
    if (data.length === 0) handlePage(1, 0);
    else handlePage(data[0]['page'], data[0]['totalPages']);
}

function handleShowProduct(response) {
    let data = response['data'];
    let html = `
    <div class="d-flex">
                <div class="card flex-grow-1">
                    <div class="card-body">
                        <img src="/image/${data['image']}" alt="Card image" style="width:100%;">
                    </div>
                </div>
                <div class="card flex-grow-1">
                    <div class="card-header">
                        <button type="button" class="btn btn-danger" onclick="none('.cover')">X</button>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title">${data['name']}</h4>
                        <p class="card-text font-weight-bold"><code>Giá tiền: ${data['cost']}</code></p>
                        <ul class="pagination">
                            <li class="page-item disabled"><a class="page-link"
                                                              onclick="cal(this.parentElement.parentElement, 'minus')">-</a></li>
                            <li class="page-item disabled"><a class="page-link amount">1</a></li>
                            <li class="page-item"><a class="page-link"
                                                     onclick="cal(this.parentElement.parentElement, 'plus')">+</a></li>
                        </ul>
                    </div>
                    <div class="card-footer">
                        <button type="button" class="btn btn-success ${data['id']}"
                        onclick="callAddItem(this.classList[2])">Thêm vào giỏ hàng</button>
                    </div>
                </div>
            </div>
    `;
    document.querySelector('.product-detail').innerHTML = html;
    document.querySelector('.add-item-success').style.display = 'none';
}

function handleShowProductAdmin(response) {
    let data = response['data'];
    let html = `
    <div class="d-flex">
                <div class="card flex-grow-1">
                    <div class="card-body">
                        <img src="/image/${data['image']}" alt="Card image" style="width:85%;">
                    </div>
                </div>
                <div class="card flex-grow-1">
                    <div class="card-header">
                        <button type="button" class="btn btn-danger" onclick="none('.base-form')">X</button>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title">${data['name']}</h4>
                        <p class="card-text font-weight-bold"><code>Giá tiền: ${data['cost']}</code></p>
                    </div>
                    <div class="card-footer">
                    </div>
                </div>
            </div>
    `;
    document.querySelector('.product-detail').innerHTML = html;
}

function handleDeleteProduct(response) {
    let data = response['data'];
    callShowAllProductsAdmin(document.querySelector('.category-now').id, data['page']);
}

// item, order
function handleShowOrder(response) {
    if (response['status']['code'] === '111') {
        none('.order');
        block('.alert-none');
    } else {
        let data = response['data'];
        let items = ``;
        for (let index in data['items']) {
            items += `
        <tr>
                    <td>
                        <kbd onclick="callShowItem(this)">${data['items'][index]['id']}</kbd>
                    </td>
                    <td><span class="span">${data['items'][index]['name']}</span></td>
                    <td class="font-weight-bold"><span class="span"><code>${data['items'][index]['cost']}</code></span></td>
                    <td>
                        <ul class="pagination ${data['items'][index]['id']}">
                            <li class="page-item"><a class="page-link"
                            onclick="callUpdateItem(this.parentElement.parentElement, 'minus')">-</a></li>
                            <li class="page-item disabled"><a class="page-link amount">${data['items'][index]['amount']}</a></li>
                            <li class="page-item "><a class="page-link"
                            onclick="callUpdateItem(this.parentElement.parentElement, 'plus')">+</a></li>
                        </ul>
                    </td>
                    <td>
                        <span class="span"><i class="fa fa-trash ${data['items'][index]['id']}" aria-hidden="true" data-toggle="modal" data-target="#myModal"
                        onclick="callDeleteItem(this)"></i></span>
                    </td>
                </tr>
        `;
        }
        document.querySelector('.order').classList.add(data['id']);
        document.querySelector('.items').innerHTML = items;
        document.querySelector("input[name='cost']").value = data['totalPrice'];
    }
}

function handleShowItem(response) {
    let data = response['data'];
    let html = `
    <div class="d-flex">
                <div class="card flex-grow-1">
                    <div class="card-body">
                        <img src="/image/${data['image']}" alt="Card image" style="width:100%;">
                    </div>
                </div>
                <div class="card flex-grow-1">
                    <div class="card-header">
                        <button type="button" class="btn btn-danger" onclick="none('.cover')">X</button>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title">${data['name']}</h4>
                        <p class="card-text font-weight-bold"><code>Giá tiền: ${data['cost']}</code></p>
                        <p class="card-text font-weight-bold"><code>Số lượng: ${data['amount']}</code></p>
                    </div>
                    <div class="card-footer">
                        
                    </div>
                </div>
            </div>
    `;
    document.querySelector('.product-detail').innerHTML = html;
}
function handleShowItemAdmin(response) {
    let data = response['data'];
    let html = `
    <div class="d-flex">
                <div class="card flex-grow-1">
                    <div class="card-body">
                        <img src="/image/${data['image']}" alt="Card image" style="width:100%;">
                    </div>
                </div>
                <div class="card flex-grow-1">
                    <div class="card-header">
                        <button type="button" class="btn btn-danger" onclick="none('.item-detail');flex('.item-list')">X</button>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title">${data['name']}</h4>
                        <p class="card-text font-weight-bold"><code>Giá tiền: ${data['cost']}</code></p>
                        <p class="card-text font-weight-bold"><code>Số lượng: ${data['amount']}</code></p>
                    </div>
                    <div class="card-footer">
                        
                    </div>
                </div>
            </div>
    `;
    document.querySelector('.product-detail').innerHTML = html;
}

function handleShowAllOrders(response) {
    let data = response['data'];
    let tbody = '';
    for (let index in data) {
        tbody += `
            <tr>
                <td>${data[index]['id']}</td>
                <td class="${data[index]['status']}">${data[index]['status']}</td>
                <td>${data[index]['totalPrice']}</td>
                <td>${data[index]['address']}</td>
                <td>${data[index]['username']}</td>
                <td>${data[index]['createTime']}</td>
                <td>
                    <button type="button" class="btn btn-success mr-2 ${data[index]['id']}" style="padding:3px 10px;"
                    onclick="callShowOrderAdmin(this)">
                    Xem</button>
                </td>
            </tr>   
        `;
    }
    document.querySelector('.orders').innerHTML = tbody;
    if (data.length === 0) handlePage(1, 0);
    else handlePage(data[0]['page'], data[0]['totalPages']);
}

function handleShowOrderAdmin(response) {
    let data = response['data'];
    let items = ``;
    for (let index in data['items']) {
        items += `
        <tr>
                    <td>
                        ${data['items'][index]['id']}
                    </td>
                    <td>${data['items'][index]['name']}</td>
                    <td class="font-weight-bold"><span class="span"><code>${data['items'][index]['cost']}</code></span></td>
                    <td>${data['items'][index]['amount']}</td>
                    <td>${data['items'][index]['createTime']}</td>
                    <td>
                        <button type="button" class="btn btn-success mr-2 ${data['items'][index]['id']}" style="padding:3px 10px;"
                        onclick="callShowItemAdmin(this)">
                    Xem</button>
                    </td>
                </tr>
        `;
    }
    document.querySelector('.items').innerHTML = items;
}























