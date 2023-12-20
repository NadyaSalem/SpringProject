
let commentSection = document.getElementById('comment')

fetch(`http://localhost:8080/api/comments`)
    .then((resp) => resp.json())
    .then(json => json.forEach(message => {
        let commentHtml = '<div class="comment text-center">\n'
        commentHtml += `<h6>${message.authorFullName}</h6>\n`
        commentHtml += `<h6 class="mb-4">${message.description}</h6>\n`
        commentHtml += '</div>\n'

        commentSection.innerHTML += commentHtml
    }))
