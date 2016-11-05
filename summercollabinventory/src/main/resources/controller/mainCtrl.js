app.controller("mainController", function($scope) {
	$scope.showReg = false;
	$scope.showLogin = true;
	$scope.showForgot = false;

	$scope.book = {
        id: 1,
        name: 'Harry Potter',
        author: 'J. K. Rowling',
        stores: [
            { id: 1, name: 'Barnes & Noble', quantity: 3},
            { id: 2, name: 'Waterstones', quantity: 2},
            { id: 3, name: 'Book Depository', quantity: 5}
        ]
    };
	
	$scope.errors = [
  		{id:1, desc: 'Please enter a value having no special characters' },
 		{id:2, desc: 'Field is Mandatory' },
 		{id:3, desc: 'Max length is 7 characters' },
 		{id:4, desc: 'Max length is 8 characters'},
 		{id:5, desc: 'Only letters, numbers and \*\!\$\# is allowed' },
 		{id:6, desc: 'Invalid date selected' },
 		{id:7, desc: 'Passwords do not match' },
 		{id:8, desc: 'Please enter valid value' },
 		{id:9, desc: 'Username and Password combination doesn\'t exist' },
 		{id:10, desc: 'Username not found' },
  	];
});