"use strict";
var pagesFolder = ''
var app=angular.module("aicteApp",['ngRoute']);
app.config(function($routeProvider,$locationProvider){
		$routeProvider
		.when('/',{
			redirectTo:"/login"
		
		})
		.when('/login',{
			templateUrl : pagesFolder + 'login.html'
			
		})
		.when('/register',{
			templateUrl :pagesFolder + 'register.html'
			
		})
		.when('/home',{
			templateUrl :pagesFolder + 'pages/home.html'
			
		})
		.when('/professional',{
			templateUrl :pagesFolder + 'personal_details.html'
			
		})
		.when('/basic',{
			templateUrl :pagesFolder + 'student.html'
			
		})
		.when('/forget',{
			templateUrl : pagesFolder + 'forget.php'
			
		});
		
		/*$locationProvider.html5Mode({
  enabled: true,
  requireBase: false
}).hashPrefix('!');
*/

		}
);

app.run(function($rootScope,$location,sessionService){
	var routes=['/home','/profile','/basic','/professional'];
	$rootScope.a=0;
	$rootScope.$on('$routeChangeStart',function(){	
			
			if(routes.indexOf($location.path()) != -1 && !sessionService.get('user')){
				$location.path('/');
				/*var connected=loginService.isLoggedIn();
				
				connected.then(function(msg){
					console.log(msg.data);
					if (!msg.data.errorCode){
						$location.path('/');
						$rootScope.a=0;
					}
					else{
						$rootScope.a=1;
					}
					
				},function(status){
					console.log(status);
				}
				);
					
*/
			}
	
	});

});
