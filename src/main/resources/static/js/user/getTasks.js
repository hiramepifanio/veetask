function onLoad() {
	var newTaskApp = new Vue(
	{
		el: '#new-task-form',
		data: {
			description: '',
			ownerUsername: ''
		},
		methods: {
			register: function(username) {
				axios
		      		.post('http://localhost:8080/api/tasks/register', {
						description: this.description,
						ownerUsername: username
					})
			}
		}
	});
	
	var app = new Vue(
	{
		el : '#tasks',
		data : {
			tasks : []
		},
		mounted () {
			axios
		      .get('http://localhost:8080/api/tasks')
		      .then(response => this.tasks = response.data)
		},
		methods: {
			check: function(task) {
				axios
		      		.put('http://localhost:8080/api/tasks/check', {id: task.id})
					.then(response => task.status = "DONNE")
			},
			uncheck: function(task) {
				axios
		      		.put('http://localhost:8080/api/tasks/uncheck', {id: task.id})
		      		.then(response => task.status = "TODO")
			},
			remove: function(task) {
				axios
		      		.delete('http://localhost:8080/api/tasks/remove/' + task.id)
		      		.then(response => this.tasks.splice(this.tasks.indexOf(task), 1))
			}
		}
	});
}







/*console.log("I will search the tasks");

var xhr = new XMLHttpRequest();

xhr.open("GET", "http://localhost:8080/user/tasks/json");

xhr.addEventListener("load", function() {
	var tasks = JSON.parse(xhr.responseText);
	console.log(tasks);
});

xhr.send();*/