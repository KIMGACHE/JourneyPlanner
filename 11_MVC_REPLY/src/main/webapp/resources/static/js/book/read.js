/**
 * 
 */

console.log('read.js...');


const replyAddBtn = document.querySelector(".reply-add-btn");
replyAddBtn.addEventListener('click',function(){
	console.log("replyAddBtn clicked..");
	
	const contents = documnet.querySelector('.reply-header textarea').value;
	
	axios.get( `${path}/book/reply/add?bookCode=${bookCode}&content=${contents}`)
	.then(resp=>
		{console.log(resp);
		documnet.querySelector('.reply-header textarea').value;
	})
	.catch(error=>{console.log(error);})
	
//	createReplyItem();
})

let i = 0;

const createReplyItem = (item) => {
    const parentNode = document.querySelector('.reply-box');
    
    console.log("Reply item created...");

    // 새로운 reply 아이템을 위한 div 생성
    const itemDiv = document.createElement('div');
    itemDiv.className = 'item';
    
    // 아이템의 왼쪽 영역 생성
    const leftDiv = document.createElement('div');
    leftDiv.className = 'left';

    const profileImg = document.createElement('img');
    profileImg.src = `${path}/profile/image?username=${item.username}`;
    profileImg.alt = 'profileImage';

    const usernameDiv = document.createElement('div');
    usernameDiv.textContent = item.username;

    leftDiv.appendChild(profileImg);
    leftDiv.appendChild(usernameDiv);

    // 아이템의 오른쪽 영역 생성
    const rightDiv = document.createElement('div');
    rightDiv.className = 'right';

    const dateDiv = document.createElement('div');
    dateDiv.className = 'date';
    dateDiv.textContent = item.createAt;

    const contentDiv = document.createElement('div');
    contentDiv.className = 'content';

    const textarea = document.createElement('textarea');
	textarea.classname ="form-control";
    textarea.cols = 10;
    textarea.rows = 2;
    textarea.readOnly = true;
    textarea.textContent = '템플릿 - ' + (++i);

    contentDiv.appendChild(textarea);

    // 버튼 그룹 생성
    const buttonGroupDiv = document.createElement('div');
    buttonGroupDiv.className = 'buttongroup';

    const thumbUpSpan = document.createElement('span');
    thumbUpSpan.className = 'material-symbols-outlined';
    thumbUpSpan.textContent = 'thumb_up';

    const thumbDownSpan = document.createElement('span');
    thumbDownSpan.className = 'material-symbols-outlined';
    thumbDownSpan.textContent = 'thumb_down';

    buttonGroupDiv.appendChild(thumbUpSpan);
    buttonGroupDiv.appendChild(thumbDownSpan);

    // 오른쪽 영역에 date, content, 버튼 그룹 추가
    rightDiv.appendChild(dateDiv);
    rightDiv.appendChild(contentDiv);
    rightDiv.appendChild(buttonGroupDiv);

    // 전체 아이템에 왼쪽과 오른쪽 영역 추가
    itemDiv.appendChild(leftDiv);
    itemDiv.appendChild(rightDiv);

    // parentNode에 itemDiv 추가
    parentNode.appendChild(itemDiv);
	
	
	
}
	
	
	const receiveReplyData  = (bookCode)=>{
		console.log(path,bookCode);
		axios.get(`${path}/book/reply/list?bookCode=${bookCode}`)
		.then(
			resp=>{
				console.log(resp.data);//true일 때
				resp.data.forEach(el=>{
					createReplyItem(el);
				})
				
			})
		.catch(error=>{console.log(error);}) //에러일 때
	}

	receiveReplyData(`${bookCode}`);