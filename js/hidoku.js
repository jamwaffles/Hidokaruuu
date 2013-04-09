Math.rangeRandom = function(min, max) {
	return Math.random() * (max - min) + min;
}

var Hidoku = Backbone.Model.extend({
	fullBoard: [],
	board: [],
	undoStack: [],
	hints: 0,
	moves: 0,
	size: 9,
	difficulty: 0,		// 0 easy, 1 medium, 2 hard, 3 Julie
	initialize: function() {
		this.max = this.size * this.size;

		this.generateFullBoard();
	},
	generateFullBoard: function() {
		for(var row = 0; row < this.size; row++) {
			var temp = [];

			this.fullBoard.push(new Array());

			for(var col = 0; col < this.size; col++) {
				temp.push((row * this.size) + col + 1);
			}

			this.fullBoard[this.fullBoard.length - 1] = _.shuffle(temp);
		}

		this.fullBoard = _.shuffle(this.fullBoard)

		console.log(this.fullBoard);
	}
});

var foo = new Hidoku;