var app = angular.module("myApp", ['ui.bootstrap']);

app.factory('Data', function(){
    return { campName: '' ,
			addrLine1: '',
			addrLine2:'',
			city:'',
			state: '',
			zip:''};
});

app.controller('FirstCtrl', function( $scope, Data ){
	$scope.Data = Data;
});

app.controller('SecondCtrl', function( $scope, Data ){
	$scope.Data = Data;
});