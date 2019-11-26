
/////////////////// required 
/*1.      Longest String
Define function: maxLength(array)
Write a JavaScript to find the longest string from a given array of strings and returns the string’s array index.
 */

function maxLength(array){
    let longestWord;

    for (let i = 0; i < array.length; i++) {
        longestWord = array[i];
        if (longestWord.length() < array[i+1]) {
            longestWord = array[i+1]
        }
    }
    return longestWord

}

/*
2.      Reverse Array
Define function: reverseArray(array)
Write a JavaScript function to reverse the elements of a given array.
 */

function reverseArray(array){
  let reversedArray = array.reverse()
  return reverseArray
}


 /*
3.     Count Vowels 
	Define function: vowelCount(string)
	 Write a JavaScript function to count the number of vowels in a given string.

*/

function vowelCount(string) {
  let counter = 0
  let i = 0
  let newString = string.toLowerCase()
  while (i<string.length()) {
    if (newString[i].includes("a") || newString[i].includes("e")  || newString[i].includes("i") || newString[i].includes("o") || newString[i].includes("u")) {
      counter++
    }
    i++
  }
}
     

/*
4.      Email Validation 	
Define function: isValidEmail(string)
Create a function that checks for a valid email format.
 */

function isValidEmail(string){
	
	let isEmail = false
	
	if(string.indexOf('@') > 0 && string.indexOf('.') > string.indexOf('@')) {
    isEmail = true
    return isEmail
  }
  return isEmail
}



/*
5.     Remove Character
	Define function: removeChar(string, index)
Write a JavaScript function to remove a character at the specified position of a given string and return the new string.
*/
function removeChar(string, i) {
  return string.substring(0, i) + string.substring(i+1);

}


/*
6.       Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort an array of numbers. You may need to look up the algorithm if you’re not familiar with it
Return the sorted array.
*/
function bubble_sort(arr){
  for(let i = 0; i < arr.length; i++){
    for(let j = 0; j < (arr.length-i-1); j++){
      let thisEle = arr[j];
      if(arr[j] > arr[j+1]){
        arr[j] = arr[j+1];
        arr[j+1] = thisEle;
      }
    }
  }
  
  return arr;
}


/*
7.    Even Number
Define function: isEven(someNum)
Return true if even, false if odd.
Challenge: Do not use % operator.
*/

function isEven(someNum){
  return someNum % 2 == 0;
} 

/*
8.   Palindrome
Define function: isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false.
*/
function isPalindrome(string) {
  var str = string;
  var palindromeReversed = string.split('').reverse().join('');
  console.log(string);
  if (palindromeReversed === str ) {
    return true;
  } else {
    return false;
  }
 }

/*
////////////// challenge questions

9. Find Leap Year
Define function: isLeapYear(date)
Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.
 
*/




/*
10.   Shapes
Define function: printShape(shape, height, character)
shape is a String and is either "Square", "Triangle", "Diamond".
height is a Number and is the height of the shape. Assume the number is odd.
character is a String that represents the contents of the shape.
Assume this String contains just one character.
Use a switch statement to determine which shape was passed in.
Use the console.log function to print the desired shape.
Example for printShape("Square", 3, "%");
%%%
%%%
%%%
Example for printShape("Triangle", 3, "$");
$
$$
$$$

Example for printShape("Diamond", 5, "*");
  *
 ***
*****
 ***
  *				
  
 
*/ 



/*
11.   Rotate Left
Define function: rotate(array, n)
Given array, rotate left n times and return array
Examples
f([1,2,3,4,5], 1) = [2,3,4,5,1]
f([1,2,3,4,5], 6) = [2,3,4,5,1]
f([1,2,3,4,5], 3) = [4,5,1,2,3]


*/


/*
12.   Balanced Brackets
 	Define function: balanced(string)

A bracket is any one of the following: (, ), {, }, [, or ]
 
The following are balanced brackets:
()
()()
(())
({[]})
 
The following are NOT balanced brackets
(
)
(()i
([)]
 
Create a function which takes a string of brackets and returns true if balanced and false if not balanced
 

*/
