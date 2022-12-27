start();
function start() {
    none('.user');
    none('.alert-cancel');
    none('.alert-pay');
    none('.alert-error');
    none('.alert-none');
    none('.cover');
    document.querySelector('.order').style.display = 'flex';
    callShowOrder();
}
function none(element) {
    document.querySelector(element).style.display = 'none';
}