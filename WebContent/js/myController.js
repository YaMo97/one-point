app.controller("aicteController",function($rootScope,$scope,$http,$location,loginService){
var pagesFolder="pages/";
	$rootScope.a=0;
	$scope.Logout= function(){
		/*var dat={
				
		}
		$http.post(ip + "OnePointVerificationSystem/Destroy",dat,config)
		.then(function(response){
			console.log(resposne.data);
			loginService.logout();
			$location.path('/').search("objectId",null).search("role",null);
			$scope.a=1;
			}
		,function(status){
			console.log(status);
		});*/
		loginService.logout();
		$rootScope.a=0;
		$location.path('/').search("objectId",null).search("role",null);
		
	}
	$scope.home=function(){
		$location.path('/home');

	}
	$scope.professional=function(){
		$location.path('/professional');

	}
	/*$scope.professional=function(){
		$scope.queryString=$location.search();
		console.log($scope.queryString);
		console.log($scope.queryString.role);
		if($scope.queryString.role=="student"){
			$location.path("/student_pro");
		}
		else if($scope.queryString.role=="faculty"){
			$location.path("/faculty_pro");
		}
		
	}*/
	$scope.basic=function(){
		$scope.queryString=$location.search();
		if($scope.queryString.role=="STUDENT"){
				$location.path("/basic");
		}
		else if($scope.queryString.role=="FACULTY"){
				$location.path("/basic");
		}
		
	}
});

app.controller("loginController",function($rootScope,$scope,$http,$location,sessionService,loginService){
	$scope.login={};
	console.log("dsad")
	$scope.login.invalid=0;
	var pagesFolder="pages/";
	$scope.Login=function(){
		console.log(10);
		var config={
		headers:{
				'Content-Type':'application/json;charset=UTF-8'
			}
		};
		dat={
			emailId:$scope.login.username,
			password:$scope.login.password
		};
		
		$http.post(ip + "OnePointVerificationSystem/Login",dat,config)   		//add servlet name(login api) 
		.then(function(response){
			
			if(response.data.status == "SUCCESS"){
				console.log(response.data);
				$rootScope.a=1;
				loginService.login(dat.emailId,response.data.message);
			}
			else{
			$scope.login.invalid = 1;;
		}
			
		}
		,function(satus){
			console.log(status);

		})
		;


		
		

	
	}
});
app.controller("registerController",function($scope,$http,$location){
		
	$scope.register={};
	$scope.states=['Jammu Kashmir','Himachal Pradesh','Punjab','Uttranchal','Delhi','Haryana','Rajasthan','Madhya Pradesh','Uttar Pradesh','Bihar','Maharashtra','Orissa','Chattisgarh','West Bengal','Jharkand','Assam','Arunachal Pradesh','Sikkim','Manipur','Meghalaya','Tripura','Nagaland','Mizoram','Kerala','Karnataka','Tamil Nadu','Andhra Pradesh','Telangana','Goa'];
	$scope.universities=[];
	$scope.colleges=[];
	$scope.c1=function(){
	$http.get(ip + "OnePointVerificationSystem/FetchCollege?state=" + $scope.register.state)
	.then(function(response){
		$scope.universities=response.data.data;
		console.log(response.data.data);
	},
	function(response){
		console.log(response);
	});	
	}
	$scope.c2=function(){
	$http.get(ip + "OnePointVerificationSystem/FetchCollege?state=" + $scope.register.state+"&university="+$scope.register.university )
	.then(function(response){
		console.log(response.data.data);
		$scope.colleges=response.data.data;
	},
	function(response){
		console.log(response);
	});
	}
	$scope.registerDetails=function(){
		console.log(1);
		var config={
			headers:{
				'Content-Type':'application/json;charset=UTF-8'
			}
		};
		
		$http.post(ip + "OnePointVerificationSystem/Register",$scope.register,config)
		.then(function(response){
			console.log('success');
			$location.path('/login');
		},
		function(response){
			console.log(response)
		});
	}

});
app.controller('homeController',function($rootScope,$http,$location,$scope){
	$rootScope.a=1;
	$scope.basic={};
	var config={
			headers:{
					'Content-Type':'application/json;charset=UTF-8'
				}
			};
			var dat={
					emailId:$location.search().objectId,
					role:$location.search().role
			}
	$http.post(ip + "OnePointVerificationSystem/FetchDetails",dat,config)
	.then(function(response){
		console.log(response.data.data[0]);
		
		$scope.basic=response.data.data[0];
		console.log($scope.basic.aadhaar_status);

		console.log($scope.basic.pan_status);

		console.log($scope.basic.bank_status);
	},function(response){
		console.log(response.status);
	});
})

app.controller('studentController',function($location,$scope,$http,$rootScope){
	$scope.editable=1;
	$rootScope.a=1;
	$scope.basic={};
	$scope.editable=1;
	$scope.faculty=0;
	$scope.student=0;
	
	if($location.search().role=="FACULTY"){
		$scope.faculty=1;
	}
	else if($location.search().role=="STUDENT"){
		$scope.student=1;
	}
	var config={
			headers:{
					'Content-Type':'application/json;charset=UTF-8'
				}
			};
			var dat={
					emailId:$location.search().objectId,
					role:$location.search().role
			}
	$http.post(ip + "OnePointVerificationSystem/FetchDetails",dat,config)
	.then(function(response){
		console.log(response.data.data[0]);
		
		$scope.basic=response.data.data[0];
	},function(response){
		console.log(response.status);
	});
	$scope.pro=function(){
		$location.path('/professional');
	}
	
	$scope.save=function(){
		//$http.post
		$scope.editable=1;
	}

	

});
app.controller("proController",function($rootScope,$http,$scope,$location){
	$scope.pro={};
	$rootScope.a=1;
	//$http.get()
	$scope.editable=1;
	
	$scope.edit=function(){
		$scope.editable=0;
	}
	$scope.back=function(){
		$location.path('/basic')
	}
	var config={
			headers:{
					'Content-Type':'application/json;charset=UTF-8'
				}
			};
			var dat={
					emailId:$location.search().objectId,
					role:$location.search().role
			}
			
	$http.post(ip + "OnePointVerificationSystem/FetchDetails",dat,config)
	.then(function(response){
		console.log(response.data.data[0]);
		
		$scope.pro=response.data.data[0];
	var da={
			
	
				aadhaarNumber:'232123321123',
				fullName:$scope.pro.full_name,
				gender:$scope.pro.gender
	}
						
				console.log(da.aadhaarNumber);
				console.log(da.fullName);
				console.log(da.gender);
				$scope.save=function(){
											$http.post(ip + "OnePointVerificationSystem/Verify",da,config )
											.then(function(response){
												console.log(response.data);
											},function(response){
												console.log(response.status);
											});
								}
	},function(response){
		console.log(response.status);
	});
	
	
	
});

app.factory('sessionService',function(){

	return{
		set:function(key,value){
			return sessionStorage.setItem(key,value);
			
		},
		get:function(key){
			return sessionStorage.getItem(key);
		},
		destroy:function(key){
		
			return sessionStorage.removeItem(key);
		}

	}
});

app.factory('loginService',function(sessionService,$location,$http,$rootScope){


	return { 
		login:function(id,role){
			if(id)
				sessionService.set('user',id);	
				$location.path('/home').search("objectId",id).search("role",role);
		},
		logout:function(){
			sessionService.destroy('user');
			$rootScope.a=0;
			
		},
		isLoggedIn:function(){
			
			return $http.get(ip + "OnePointVerificationSystem/CheckLogin")
					
		}
	}
});

