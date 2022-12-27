start();
function start() {
    none('.user');
    none('.base-form');
    callShowAllCategoriesName(1);
}
function none(token) {
    document.querySelector(token).style.display = 'none';
}
