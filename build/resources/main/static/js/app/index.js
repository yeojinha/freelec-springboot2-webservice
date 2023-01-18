let main = {
    init : function (){
        let _this = this;
            $('#btn-save').on('click',function(){//btn-save라는 id를 가진 HTML 엘리먼트에 CLICK 이벤트가 발생할 때, save
            //function을 실행하도록 이벤트를 등록
            _this.save();
            });
            $('#btn-update').on('click',function(){//btn-update라는 id를 가진 HTML 엘리먼트에 CLICK 이벤트가 발생할 때, update
            //function을 실행하도록 이벤트를 등록
            _this.update();
            });
            $('#btn-delete').on('click',function(){
            _this.delete();
            });
    },
    save: function(){
        let data = {
            title: $('#title').val(),//이러한 데이터가 data.title에 등록
            author: $('#author').val(),
            content: $('#content').val()
        };
//data 객체 안에, title, author, content라는 value가 존재함
        $.ajax({
            type: 'POST',//POST로 보냄
            url: '/api/v1/posts',//POST로 data 객체 보낼 url
            dataType: 'json',//json type
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)//json type를 stringify 해줌
            //중요 포인트는 변수를 같게 해야, 서버측 RequestBody에서 converter가 정상작동하여, mapping 해줌
            /*
            PostsApiController에서 받는 RequestBody의 param인 PostsSaveRequestDto는 아래와 같아.
            자세히 보면 data 객체의 value와 동일한 변수명을 가지고 있다, 그리하여 mapping이 가능한 것이다.
            public PostsSaveRequestDto(String title, String content, String author){
                    this.title=title;
                    this.content=content;
                    this.author=author;
                }
            */
        }).done(function(){
            alert('글이 등록되었습니다.');
            window.location.href = '/'; //1
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    update: function(){
        let data = {
        title: $('#title').val(),//위와 같은 데이터가 data.title에 등록
        content: $('#content').val()
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',//PostsApiController에 있는 API에서 이미 @PutMapping으로 선언했기 때문에, PUT을 사용해야한다.
            url: '/api/v1/posts/'+id, //수정할 게시물을 id로
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href='/';
        }).fail(function(error){
        alert(JSON.stringify(error));
        })
    },
    delete: function(){
        let id =$('#id').val();

        $.ajax({
            type:'DELETE',
            url:'/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=uft-8'
        }).done(function(){
            alert('글이 삭제되었습니다');
            window.location.href='/';
        }).fail(function(){
            alert(JSON.stringify(error));
        })
    }
    /*
    REST에서 CRUD는 아래와 같이 HTTP Method에 매핑된다
    생성(Create) - POST
    읽기(Read) - READ
    수정(Update) - PUT
    삭제(Delete) - DELETE
    */
};
main.init();