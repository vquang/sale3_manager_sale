start()
function start() {
    none('.user');
    none('.cover');
    callShowAllCategories(1);
}
function none(element) {
    document.querySelector(element).style.display = 'none';
}