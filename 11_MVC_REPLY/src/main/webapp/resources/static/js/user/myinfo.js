/**
 * 
 */

console.log("myinfo.js")

const formData = new FormData();

//
const profileBoxEl = document.querySelector(".profileBox");
const profileUploadBtn = document.querySelector(".profileUploadBtn");
//

// drag , dragover dragstart dragend drop




profileBoxEl.addEventListener('dragover',(e)=>{
    console.log("dragover",e)
    e.preventDefault();    
})
profileBoxEl.addEventListener('drop',(e)=>{
    console.log("drop",e)
    console.log("drop",e.dataTransfer)
    console.log("drop",e.dataTransfer.files)
    console.log("drop",e.dataTransfer.files[0])
    const file = e.dataTransfer.files[0];

    if(file.type.startsWith("image/")){
        console.log("IMAGE파일..")
        
		//기존 img 태그 삭제
		while(profileBoxEl.firstChild){
			profileBoxEl.removeChild(profileBoxEl.firstChild);
		}
		
        const imgTg=document.createElement('img')
        imgTg.src=URL.createObjectURL(file)
        imgTg.setAttribute("style","width:100%;height:100%;border-radius:50%;")
        profileBoxEl.appendChild(imgTg)
		
		//
		formData.append("profileImage",file);	
    }
	
    e.preventDefault();

})



//이미지 업로드 버튼 클릭 이벤트
profileUploadBtn.addEventListener("click",function(e){
	
	console.log("profileUploadBtn clicked..");
	
	
	//post('url','param','header-type')
	axios
	.post(
		path+'/user/profile/upload' ,
		formData, 
		{ headers:{'Content-Type' :'multipart/form-data'} }
	)
	.then(resp=>{ console.log(resp); })
	.catch(err=>{ console.log(err); } )
	
	
});









