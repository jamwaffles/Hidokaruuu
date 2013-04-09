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
		
	}
});

var foo = new Hidoku;