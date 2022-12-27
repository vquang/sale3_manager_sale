start();
function start() {
    none('.user');
    none('.base-form');
    callShowAllOrders(1);
}
function none(token) {
    document.querySelector(token).style.display = 'none';
}
