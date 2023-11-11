function bubbleSort(arr) {
    let len = arr.length;
    let swapped;
    do {
        swapped = false;
        for (let i = 0; i < len - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                // Swap the elements
                let temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                swapped = true;
            }
        }
    } while (swapped);
}

// Test the Bubble Sort algorithm
const arr = [64, 34, 25, 12, 22, 11, 90];
console.log("Original Array:", arr);
bubbleSort(arr);
console.log("Sorted Array:", arr);
