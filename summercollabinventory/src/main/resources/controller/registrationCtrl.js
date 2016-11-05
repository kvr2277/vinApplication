app.controller("registrationController", function($scope) {
	$scope.master = {};
	$scope.invalidFName = false;
	$scope.invalidLName = false;
	$scope.invalidUser = false;
	$scope.invalidPassword = false;
	$scope.invalidRePassword = false;
	$scope.passwordMatched = false;
	$scope.pwdMatchedDesc = "Passwords Matched";
	$scope.showSubmit = true;
	
	$scope.validateField = function(fieldName, fieldValue){
		if(fieldName == 'fName'){
			fieldValue = $scope.user.fName;
			if(/[^a-zA-Z ]/.test( fieldValue ) || fieldValue.length == 0) {
				$scope.invalidFName = true;
				$scope.fNameErrDesc = $scope.$parent.errors[7].desc;	
			}else{
				$scope.invalidFName = false;
			}
		}else if(fieldName == 'lName'){
			fieldValue = $scope.user.lName;
			if(/[^a-zA-Z ]/.test( fieldValue ) || fieldValue.length == 0) {
				$scope.invalidLName = true;
				$scope.lNameErrDesc = $scope.$parent.errors[7].desc;
			}else{
				$scope.invalidLName = false;
			}
		}else if(fieldName == 'user'){
			fieldValue = $scope.user.userName;
			if(/[^a-zA-Z0-9]/.test( fieldValue ) || fieldValue.length == 0) {
				$scope.invalidUser = true;
				$scope.userNameErrDesc = $scope.$parent.errors[0].desc;
			}else{
				$scope.invalidUser = false;
			}
			
		}else if(fieldName == 'password'){
			fieldValue = $scope.user.password;
			if(/[^a-zA-Z0-9\*\!\$\#]/.test(fieldValue) || fieldValue.length == 0) {
				$scope.invalidPassword = true;
				$scope.pwdErrDesc = $scope.$parent.errors[4].desc;
			}else{
				$scope.invalidPassword = false;
			}
		}else if(fieldName == 'repassword'){
			fieldValue = $scope.user.repassword;
			if(fieldValue == $scope.password) {				
				$scope.passwordMatched = true;
				$scope.invalidRePassword = false;
				
			}else{			
				$scope.passwordMatched = false;
				$scope.invalidRePassword = true;
				$scope.rePwdErrDesc = $scope.$parent.errors[6].desc;

			}
		}else if(fieldName == 'ques1Resp'){
			fieldValue = $scope.user.myQues1;
			if(/[^a-zA-Z0-9]/.test(fieldValue) || fieldValue.length == 0) {
				$scope.invalidQues1Resp = true;
				$scope.ans1ErrDesc = $scope.$parent.errors[0].desc;
			}else{
				$scope.invalidQues1Resp = false;
			}
		}else if(fieldName == 'ques2Resp'){
			fieldValue = $scope.user.myQues2;
			if(/[^a-zA-Z0-9]/.test(fieldValue) || fieldValue.length == 0) {
				$scope.invalidQues2Resp = true;
				$scope.quesRespErrorcode = 1;
				$scope.ans2ErrDesc = $scope.$parent.errors[0].desc;
			}else{
				$scope.invalidQues2Resp = false;
			}
		}
	}
	
	$scope.questions = [
		{id:0, desc: 'What is your pets name?' },
		{id:1, desc: 'What is your mothers maiden name?' },
		{id:2, desc: 'In which city you are born?' },
		{id:3, desc: 'What is your favorite sport?'},
		{id:4, desc: 'What is your favorite color?' },
		{id:5, desc: 'In what city did you meet your spouse/best friend first?' },
	  ];
	
	
	$scope.questions1 = angular.copy($scope.questions);	
	$scope.questions2 = angular.copy($scope.questions);
	$scope.myQues1 = $scope.questions1[1];
	$scope.myQues2 = $scope.questions2[1];
	$scope.prevvalue=null;

	
	$scope.updateQuestions = function(quesField,quesValue){	
		//TODO - update question2 based on selection of 1
		alert(quesValue.desc);
		if(quesField == 'myQues1'){	
			var index = $scope.questions1.indexOf(quesValue);
			$scope.ques1Resp = "";
			$scope.questions2.splice(index,1);
			/*if($scope.prevvalue){
				$scope.questions2.splice(index,1);
			}*/
		}else{
			$scope.ques2Resp = "";
		}
		
		
		/*if(quesField == 'myQues1'){
			$scope.questions2 = angular.copy($scope.questions);
			for(var i = $scope.questions.length - 1; i >= 0; i--){
		        if($scope.questions[i].desc == quesValue.desc){
		        	$scope.questions2.splice(i,1);
		        }
		    }
			$scope.myQues1Selected = true;
			if($scope.myQues2Selected == true){
				alert(1);
				$scope.myQues2 = $scope.myQues2SelectedVal;
			}
		}else{
			$scope.myQues2SelectedVal = quesValue;
			$scope.myQues2Selected = true;
			$scope.questions1 = angular.copy($scope.questions);
			for(var i = $scope.questions.length - 1; i >= 0; i--){
		        if($scope.questions[i].desc == quesValue.desc){
		        	$scope.questions1.splice(i,1);
		        }
		    }
		}	*/	
	}
	
	$scope.submitSignup = function(){	
		
	}	
	
	
	$scope.cancelSignup = function(){	
		$scope.user = angular.copy($scope.master);
		$scope.$parent.showReg = false;
		$scope.$parent.showLogin = true;
	}
	
	$scope.resetSignup = function(){	
		$scope.questions2 = angular.copy($scope.questions);
		$scope.user = angular.copy($scope.master);
	}
	  $scope.today = function() {
		    $scope.dt = new Date();
		  };
		  $scope.today();

		  $scope.clear = function () {
		    $scope.dt = null;
		  };

		  // Disable weekend selection
		  $scope.disabled = function(date, mode) {
		    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
		  };

		  $scope.toggleMin = function() {
		    $scope.minDate = $scope.minDate ? null : new Date();
		  };
		  $scope.toggleMin();

		  $scope.open = function($event) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope.opened = true;
		  };

		  $scope.dateOptions = {
		    formatYear: 'yy',
		    startingDay: 1
		  };

	$scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	  $scope.format = $scope.formats[0];
	  
	 
	
	
});