function createNewDish() {
    let randomNumber = Math.floor(Math.random() * Math.floor(100));
    let data = {
        name: "dish-" + randomNumber,
        description: "Description",
        price: (randomNumber / 10) + (randomNumber % 10)
    };

    fetch("/restaurant/api/dishes", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        console.log("Request complete! response:", res);
    });
}

function getAllDishes() {
    let data = ""
    fetch("/restaurant/api/dishes")
        .then(res => data = res.json())
        .then(dishes => {
            let dishesDiv = document.getElementById("dishes");
            dishesDiv.innerHTML = "";
            for (let i = 0; i < dishes.length; i++) {
                let dish = dishes[i];
                dishesDiv.innerHTML += (dish.id + " " + dish.name + " " + dish.description + " " + dish.price + "<br>");
            }
        });
}

function deleteAllDishes() {
    fetch("/restaurant/api/dishes", {
        method: "DELETE"
    }).then(r => console.log(r))
}