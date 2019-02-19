/**
 * Created by yuzhe on 2/5/18.
 */
var price = 10;
java.lang.String.format('price: %.2f', Number(price));

var intArray = Java.type('int[]');
var numbers = new intArray(10);
numbers[0] = 42;
print(numbers[0]);

var names = java.util.Arrays.asList('Fred', 'Wilma', 'Barney');
var first = names[0];
names[0] = 'Duke';

var scores = new java.util.HashMap;
scores['Fred'] = 10;

var words = ['one', 'two', 'three'];
java.util.Arrays.sort(words, function (x, y) {
   return java.lang.Integer.compare(x.length, y.length);
});

var RandomIterator = Java.extend(java.util.Iterator, {
    next: function () {
        return Math.random();
    },
    hasNext: function () {
        return true;
    }
});

var task = new java.lang.Runnable(function () {
    print('Hello, I\'m task.');
});

var Thread =  java.lang.Thread;
var thread = new Thread(task);
thread.start();

var str = new java.lang.String("abcdefg");
var subStr = str.substring(1, 3);
print(subStr.getClass());
print(java.lang.String.class.cast(subStr));

