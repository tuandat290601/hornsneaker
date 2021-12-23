// set default size
document.querySelector("#click-btn").classList.add("active")
changeHref(document.querySelector("#click-btn").innerHTML)

const size = document.querySelector("#card-size");
size.addEventListener("click", function (e) {
    e.preventDefault;
    //Change active
    const divTag = document.querySelector("#card-size").querySelectorAll("button");
    divTag.forEach((el) => el.classList.remove("active"));
    e.target.classList.add("active");

    // Change href of add to cart button
    changeHref(e.target.innerHTML)
});

function changeHref(siz) {
    let link = document.getElementById("add-btn").href;
    const i = link.indexOf("?size=");
    if (i != -1) link = link.substring(0,i)
    link += "?size=" + siz
    document.getElementById("add-btn").href = link
}