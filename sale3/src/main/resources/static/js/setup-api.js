// setup
let options = {
    method: '',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': ''
    },
    body: ''
};
function setApi(api, options, callback) {
    fetch(api, options)
        .then(function(response) {
            return response.json();
        })
        .then(callback);
}
// call
function callApi(api, method, data, callback) {
    options['method'] = method;
    if(data === '') delete options['body'];
    else options['body'] = JSON.stringify(data);
    setApi(api, options, callback);
}