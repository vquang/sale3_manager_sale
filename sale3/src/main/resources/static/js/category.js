start();
function start() {
    none('.user');
    none('.base-form');
    none('.delete-error');
    callShowAllCategoriesAdmin(1);
}
function none(token) {
    document.querySelector(token).style.display = 'none';
}
