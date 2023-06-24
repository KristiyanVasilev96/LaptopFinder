const offerId = document.getElementById('offerId').value;
const commentForm = document.getElementById('commentForm');
const postCommentButton = document.getElementById('postComment');
const messageInput = document.getElementById('message');
commentForm.addEventListener("submit", handleFormSubmission);

const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;

const commentContaier = document.getElementById('commentCtnr');

async function handleFormSubmission(event) {
    event.preventDefault();

    const messageVal = messageInput.value;

    if (messageVal.trim().length > 0) {
        fetch(`http://localhost:8000/api/${offerId}/comments`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeaderName]: csrfHeaderValue
            },
            body: JSON.stringify({
                message: messageVal
            })
        })
            .then(res => res.json())
            .then(data => {
                messageInput.value = "";
                commentContaier.innerHTML += comementAsHtml(data);
                updatePostCommentButtonColor();
            });
    }
}

function comementAsHtml(comment) {
    let commentHtml = '<div>\n';
    commentHtml += `<h4>${comment.authorName}</h4>\n`;
    commentHtml += `<p>${comment.message}</p>\n`;
    commentHtml += '</div>\n';

    return commentHtml;
}

fetch(`http://localhost:8000/api/${offerId}/comments`, {
    headers: {
        "Accept": "application/json"
    }
})
    .then(res => res.json())
    .then(data => {
        for (let comment of data) {
            commentContaier.innerHTML += comementAsHtml(comment);
        }
        updatePostCommentButtonColor();
    });

function updatePostCommentButtonColor() {
    const messageVal = messageInput.value;
    if (messageVal.trim().length === 0) {
        postCommentButton.style.backgroundColor = 'grey';
    } else {
        postCommentButton.style.backgroundColor = 'blue';
    }
}

messageInput.addEventListener('input', updatePostCommentButtonColor);