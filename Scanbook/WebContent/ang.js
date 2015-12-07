angular.module('ui.bootstrap.demo', ['ngAnimate', 'ui.bootstrap']);

angular.module('ui.bootstrap.demo').controller('AccordionCtrl', function ($scope,$http) {

	$scope.myValue = false;
	this.dynamicFrom = "";
	this.dynamicdelete = "";
	$scope.getLoad = "Load";

	$scope.rate = 7;
	$scope.max = 10;
	$scope.isReadonly = false;

	$scope.hoveringOver = function(value) {
		$scope.overStar = value;
		$scope.percent = 100 * (value / $scope.max);
	};



//	User Login Validation
	function validateLogin(userid ) {

		if(userid === "")
		{
			alert('Please Login using your Gmail');
			return false;
		}
		return true;
	}

//	Save Book Details
	$scope.Save = function() {
		var userid = $('#loginId').text();
		if(validateLogin($.trim(userid)))
		{
			var scode = this.scode;
			var stitle = this.stitle;
			var sauthor = this.sauthor;
			var spages = this.spages;
			var sread = this.sread;
			var value = $scope.overStar;


			$http({
				method: 'GET',
				url: 'http://localhost:8080/addbook',
				params: {code: scode,title: stitle,author: sauthor,page: spages,read: sread,userid: userid,rating: value}
			}).then(function successCallback(response) {
				var res = response.data;
				if(res === true)
				{
					alert('Book saved successfully!!!');	

				}
				else
				{
					alert('Save unsuccessfull - Please check if Barcode already exists!!!');
				}
			}, function errorCallback(response) {
				alert('Sorry there is a Technical problem in the system we will solve it ASAP!!');
			});
			clearAllAdd();
		}
	}

//	View Book Details
	$scope.viewBook = function() {
		var userid = $('#loginId').text();
		if(validateLogin($.trim(userid)))
		{
			var search = this.dynamicFrom;
			if(search === "")
			{
				alert('Please enter the Barcode to search');
			}
			else
			{
				$http({
					method: 'GET',
					url: 'http://localhost:8080/viewbook',
					params: {barcode: search,userid: userid}
				}).then(function successCallback(response) {
					var res = response.data;
					if(res === "")
					{
						alert('Sorry!!..No such record exists');
						clearAll();
					}
					else
					{

						$scope.myValue = true;
						$scope.vmcode = " "+res.barcode;
						$scope.vmtitle = ' '+res.title;
						$scope.vmauthor =' '+ res.author;
						$scope.vmpages = ' '+res.no_Of_Page;
						$scope.vmread = res.hasRead;
						$scope.vrate = res.rating;
						$scope.vpercent = 100 * (res.rating/ $scope.max);

					}
				}, function errorCallback(response) {
					alert('Sorry there is a Technical problem in the system we will solve it ASAP!!');
				});
				this.dynamicFrom = '';
			}
		}
	}

//	Delete Book Details
	$scope.deleteBook = function() {
		var userid = $('#loginId').text();
		if(validateLogin($.trim(userid)))
		{
			var search = this.dynamicdelete;
			if(search === "")
			{
				alert('Please enter the Barcode to delete');
			}
			else
			{
				$http({
					method: 'GET',
					url: 'http://localhost:8080/delbook',
					params: {barcode: search,userid: userid}
				}).then(function successCallback(response) {
					var res = response.data;
					if(res === true)
					{
						alert('Deleted Successfully!!!');

					}
					else
					{
						alert('No book exists with the given barcode!!');
					}
				}, function errorCallback(response) {
					alert('Sorry there is a Technical problem in the system we will solve it ASAP!!');
				});
				this.dynamicdelete = '';
			}
		}
	}


//	my Book
	$scope.getBook = function() {
		var userid = $('#loginId').text();
		if(validateLogin($.trim(userid)))
		{
			$http({
				method: 'GET',
				url: 'http://localhost:8080/mybook',
				params: {userid: userid}
			}).then(function successCallback(response) {
				var res = response.data;
				if(res === null)
				{
					alert('Loading failed!!!');
				}	
				else
				{
					var arr1 = [];
					var arr2 = [];
					var result = [];
					$("#myTable tr").remove();
					var table = document.getElementById("myTable");

					for(var i in res)
					{
						var row = table.insertRow(i);
						var cell1 = row.insertCell(0);
						var cell3 = row.insertCell(2);
						cell1.innerHTML = res[i].barcode;

						cell3.innerHTML = res[i].title;

						//		arr1.push(res[i].barcode)
						//	arr2.push(res[i].title)
					}


					/*$scope.noWrapSlides = false;
					var slides = $scope.slides = [];
					$scope.addSlide = function() {
						slides.push({
							image: 'http://1.bp.blogspot.com/_jxLfB9jk3hI/S91qQZuskeI/AAAAAAAAAGw/tRj-LkJJzJA/s1600/Plain+Book1.jpg',
							code: arr1 [slides.length % arr1.length],
							title: arr2 [slides.length % arr1.length]
						});
					};
					for (var i=0; i<arr1.length; i++) {
						$scope.addSlide();
					}
					 */
					alert('Inventory loaded successfully!!!');	

					$scope.getLoad = "Re-Load";
				}
			}, function errorCallback(response) {
				alert('Sorry there is a Technical problem in the system we will solve it ASAP!!');
			});
		}
	}

	function getMyInv(userid)
	{

	}

	$scope.logout = function() {
		clearAllView();
		clearAllDel();
		clearAllAdd();
	}

//	Clear View Click
	$scope.Clear = function() {
		clearAllView();
	}

//	Clear View 
	function clearAllView()
	{
		$scope.myValue = false;
		$scope.vmcode = "";
		$scope.vmtitle = "";
		$scope.vmauthor ="";
		$scope.vmpages = "";
		this.dynamicFrom = '';
	}

//	Clear Delete
	function clearAllDel()
	{
		this.dynamicdelete = '';
	}

//	Clear My Book
	function clearAllDel()
	{
		$scope.getLoad = "Load";

	}


//	Clear Add
	function clearAllAdd()
	{
		$('#code').val('');
		$('#title').val('');
		$('#author').val('');
		$('#pages').val('');
		this.scode = "";
		this.stitle = "";
		this.sauthor = "";
		this.spages = "";

		$scope.scode = "";
		$scope.stitle = "";
		$scope.sauthor ="";
		$scope.spages = "";
		this.sread = false;
	}
});