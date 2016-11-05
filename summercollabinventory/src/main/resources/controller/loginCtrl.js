app.controller("mainController", function($scope) {
	$scope.showReg = false;
	$scope.showLogin = true;
	$scope.showForgot = false;
	
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
app.controller("loginController", function($scope, $http, Data) {
	$scope.user = {
		username:'xyz', 
		password:'aqbc'
	};
	$scope.deposit = 4;
	$scope.user.username= "Vinod";
	$scope.user.password="Kisanagaram";
	$scope.invalidUser = false;
	$scope.invalidPassword = false;
	$scope.invalidUserPassword = false;
	$scope.showSubmit = true;
	
	$scope.screen ={};
	$scope.invalidRePassword = false;
	$scope.passwordMatched = false;
	
	$scope.pwdMatchedDesc = "Passwords Matched";
	$scope.enteredInvalidAnswerDesc = "Invalid answer entered";
	
	
	$scope.validateField = function(fieldName){
		if(fieldName == 'username'){
			
			fieldValue = $scope.user.username;
			if(/[^a-zA-Z0-9]/.test( fieldValue ) || fieldValue.length == 0) {
				//alert("2");
				$scope.invalidUser = true;
				$scope.usernameErrDesc = $scope.$parent.errors[0].desc;		
			}else{
				$scope.invalidUser = false;
			}
			$scope.evalSubmit();
		}else if(fieldName == 'password'){
			fieldValue = $scope.user.password;
			if(/[^a-zA-Z0-9\*\!\$\#]/.test(fieldValue) || fieldValue.length == 0) {
				$scope.invalidPassword = true;
				$scope.passwordErrDesc = $scope.$parent.errors[4].desc;
				
			}else{
				$scope.invalidPassword = false;
			}
			$scope.evalSubmit();
		}
		else if(fieldName == 'quesResp'){
			fieldValue = $scope.user.quesResp;
			if(/[^a-zA-Z0-9]/.test( fieldValue ) || fieldValue.length == 0) {
				$scope.invalidQuesResp = true;
				$scope.ansErrDesc = $scope.$parent.errors[0].desc;	
			}else{
				$scope.invalidQuesResp = false;
			}
		}
		else if(fieldName == 'repassword'){
			fieldValue = $scope.user.repassword;
			if(fieldValue == $scope.user.password) {
				$scope.passwordMatched = true;
				$scope.invalidRePassword = false;
			}else{
				$scope.passwordMatched = false;
				$scope.invalidRePassword = true;
				$scope.rePwdErrDesc = $scope.$parent.errors[6].desc;
			}
		}
	};
	
	/*$scope.submitUser = function(user,password){
		alert(user+" "+password);
		if(user != 'admin' && password != 'admin'){
			$scope.userPasswordErrDesc = $scope.$parent.errors[8].desc;
			$scope.invalidUserPassword = true;
			$scope.showSubmit = false;
		}else{
			
		}
	};*/
	
	$scope.submitUser = function($scope, Data, $rootScope){
		alert($rootScope.deposit);
		// use $.param jQuery function to serialize data from JSON 
        var data = {
            uName: $scope.user.username,
            pwd: $scope.user.password
        };

        var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

		$http.post('/login/validate', data, config)
            .success(function (data, status, headers, config) {
                $scope.Data = data;
                $scope.showSubmit = true;
            })
            .error(function (data, status, header, config) {
            	$scope.showHome=true;
             //    $scope.ResponseDetails = "Data: " + data +
             //        "<hr />status: " + status +
             //        "<hr />headers: " + header +
             //        "<hr />config: " + config;
            });
	};
	
	$scope.evalSubmit = function(){
		if(!$scope.invalidUser && !$scope.invalidPassword ){
			$scope.showSubmit = true;
		}else{
			$scope.showSubmit = false;
		}
	};
	$scope.createUser = function(){		
		$scope.$parent.showReg = true;
		$scope.$parent.showLogin = false;
	};
	
	$scope.showForgotPwd = function(){	
		//alert(1);
		$scope.user.username = '';
		$scope.user.password = '';
		//alert(2);
		$scope.$parent.showForgot = true;
		$scope.$parent.showLogin = false;
		//alert(3);
	};	

	$scope.cancelForgot = function(){	
		$scope.user = angular.copy($scope.master);
		$scope.screen = angular.copy($scope.master);
		$scope.$parent.showForgot = false;
		$scope.$parent.showLogin = true;
		$scope.forgotPwdForm.$setPristine();
	};
	
	$scope.retrieveSecurity = function(){
		
		if($scope.showValidate == true){
			//alert(1);
			return false;
		}
		$scope.avoidUserKeyUpCall = true;
		
		//TODO - Validate username
		if($scope.user.username != 'admin'){
			$scope.invalidUsername=true;
			$scope.usernameErrDesc = $scope.$parent.errors[9].desc;
			return false;
		}	
		
		$scope.secureQuestion1 = 'What is your pets name?';
		$scope.secureQuestion2 = 'What is your favorite sport?';
		$scope.secureAnswer1 = 'tommy';
		$scope.secureAnswer2 = 'cricket';
		$scope.secureQuestion = angular.copy($scope.secureQuestion1);
		$scope.screen.showSecurity = true;
		$scope.showValidate = true;
		$scope.showRetrieve = false;
		$scope.user.quesResp = '';
		$scope.invalidQuesResp = false;
		$scope.invalidSecureAnswer = false;
	};
	
	$scope.clearSecurity = function(){	
	
		$scope.screen.showSecurity = false;
		$scope.user.ques1Resp = '';
		$scope.user.ques2Resp = '';
	};
	
	$scope.validateSecurity = function(){	
		$scope.ansCount = 0;
		$scope.avoidSecureKeyUpCall = true;
		
		if($scope.secureQuestion == $scope.secureQuestion1){
			if($scope.user.quesResp == $scope.secureAnswer1){
				//validation successful
				$scope.showPwdDiv = true;
				$scope.showValidate = false;
			}else{				
				$scope.invalidSecureAnswer = true;
			}
		}else{
			if($scope.user.quesResp == $scope.secureAnswer2){
				//validation successful
				$scope.showPwdDiv = true;
				$scope.showValidate = false;
			}else{
				$scope.invalidSecureAnswer = true;
			}
		}
	};
	
	$scope.changeQuestion = function(){	
		
		$scope.user.quesResp = '';
		$scope.invalidSecureAnswer = false; 
		if($scope.secureQuestion == $scope.secureQuestion1){
			$scope.secureQuestion = angular.copy($scope.secureQuestion2);
		}else{
			$scope.secureQuestion = angular.copy($scope.secureQuestion1);
		}
	};
	
	$scope.userKeyup = function(){	
		
		if($scope.avoidUserKeyUpCall != true){
			//alert(3);
			$scope.invalidUsername=false;
			$scope.clearSecurity(); 
			$scope.showRetrieve= true;
			$scope.showValidate = false;
			$scope.invalidSecureAnswer = false; 
			
			if($scope.showPwdDiv == true){
				$scope.showPwdDiv = false;
				$scope.showValidate = true;
				$scope.invalidSecureAnswer = false;
				$scope.user.password = '';
				$scope.user.repassword = ''; 
				$scope.invalidRePassword = false;
				$scope.passwordMatched = false;
			}
		}else{
			$scope.avoidUserKeyUpCall = false;
		}
	};
	
	$scope.secureKeyup = function(){	
		if($scope.avoidSecureKeyUpCall != true){
			$scope.invalidQuesResp=false;
			$scope.invalidSecureAnswer = false; 
			$scope.ansCount= $scope.ansCount +1;
			if($scope.showPwdDiv == true){
				$scope.showPwdDiv = false;
				$scope.showValidate = true;
				$scope.invalidSecureAnswer = false;
				$scope.user.password = '';
				$scope.user.repassword = ''; 
				$scope.invalidRePassword = false;
				$scope.passwordMatched = false;
			}
		}else{
			$scope.avoidSecureKeyUpCall = false;
		}
	}
	
	$scope.initForgot = function(){	
		$scope.showRetrieve= true;
	}
	
	$scope.submitForgot = function(){	
		alert(4);
		
	};
	
	$scope.forgotPwdFormSubmit = function(){
		
		if($scope.showRetrieve == true){
			
			$scope.retrieveSecurity();
		}else if($scope.showValidate == true && $scope.ansCount > 1){
			$scope.validateSecurity();
		}
	}
	
	
});