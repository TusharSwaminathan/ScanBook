




angular.module('ui.bootstrap.demo', ['ngAnimate', 'ui.bootstrap']);


angular.module('ui.bootstrap.demo').controller('DatepickerDemoCtrl', function($scope, $http) {

	//alerts
	$scope.alerts = [];

	$scope.addAlert = function() {
		$scope.alerts.push({msg: 'Another alert!'});
	};

	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};

	$scope.insertData = function() {

		var title= $scope.dynamicTitle.content; 
		var desc = $scope.dynamicDesc.content;
		var fromperson = $scope.dynamicFrom.content;
		var postdate = new Date();
		var eventdate = $scope.dt; 
		var eventtime = $scope.mytime.getHours()+':'+$scope.mytime.getMinutes()
		
		$scope.title = eventtime;
			$http({
				method: 'GET',
				url: 'SlobMain.do?title='+title+'&desc='+desc+'&fromperson='+fromperson+'&postdate='+postdate+'&eventdate='+eventdate+'&eventtime='+eventtime
			}).then(function successCallback(response) {
				$scope.alerts.push({type: 'success', msg: 'Message Posted Successfully!!!'});
			}, function errorCallback(response) {
			});
	}



	//datetime
	$scope.mytime = new Date();
	$scope.hstep = 1;
	$scope.mstep = 15;
	$scope.title = 'ok';

	$scope.options = {
			hstep: [1, 2, 3],
			mstep: [1, 5, 10, 15, 25, 30]
	};

	$scope.ismeridian = true;
	$scope.toggleMode = function() {
		$scope.ismeridian = ! $scope.ismeridian;
	};

	$scope.update = function() {
		var d = new Date();
		d.setHours( 14 );
		d.setMinutes( 0 );
		$scope.mytime = d;
	};

	$scope.changed = function () {
		$log.log('Time changed to: ' + $scope.mytime);
	};

	$scope.clear = function() {
		$scope.mytime = null;
	};

	$scope.today = function() {
		$scope.dt = new Date();
	};
	$scope.today();

	$scope.clear = function () {
		$scope.dt = null;
	};


	$scope.toggleMin = function() {
		$scope.minDate = $scope.minDate ? null : new Date();
	};
	$scope.toggleMin();
	$scope.maxDate = new Date(2020, 5, 22);

	$scope.open = function($event) {
		$scope.status.opened = true;
	};

	$scope.setDate = function(year, month, day) {
		$scope.dt = new Date(year, month, day);
	};

	$scope.dateOptions = {
			formatYear: 'yy',
			startingDay: 1
	};

	$scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	$scope.format = $scope.formats[0];

	$scope.status = {
			opened: false
	};

	var tomorrow = new Date();
	tomorrow.setDate(tomorrow.getDate() + 1);
	var afterTomorrow = new Date();
	afterTomorrow.setDate(tomorrow.getDate() + 2);
	$scope.events =
		[
{
	date: tomorrow,
	status: 'full'
},
{
	date: afterTomorrow,
	status: 'partially'
}
];

	$scope.getDayClass = function(date, mode) {
		if (mode === 'day') {
			var dayToCheck = new Date(date).setHours(0,0,0,0);

			for (var i=0;i<$scope.events.length;i++){
				var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

				if (dayToCheck === currentDay) {
					return $scope.events[i].status;
				}
			}
		}

		return '';
	};

});



