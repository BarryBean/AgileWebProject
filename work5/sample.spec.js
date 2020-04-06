describe('todo test', function () {
    let page;

    before (async function () {
      page = await browser.newPage();
      await page.goto('http://127.0.0.1:7001/');
    });
  
    after (async function () {
      await page.close();
    });
    //添加第一个任务homewrok
    it('should have correct title-1', async function() {
        expect(await page.title()).to.eql('Koa • Todo');
    })
    it('should new todo correct', async function() {
      await page.click('#new-todo', {delay: 500});
      await page.type('#new-todo', 'new todo item', {delay: 50});
      await page.keyboard.press("Enter");
      let todoList = await page.waitFor('#todo-list');
      const expectInputContent = await page.evaluate(todoList => todoList.lastChild.querySelector('label').textContent, todoList);
      expect(expectInputContent).to.eql('new todo item');
    }) 
	//添加第二个任务todo list item
    it('should have correct title', async function() {
      expect(await page.title()).to.eql('Koa • Todo');
    })
    it('should new todo correct-2', async function() {
      await page.click('#new-todo', {delay: 500});
      await page.type('#new-todo', 'homework', {delay: 50});
      await page.keyboard.press("Enter");
      let todoList = await page.waitFor('#todo-list');
      const expectInputContent = await page.evaluate(todoList => todoList.firstChild.querySelector('label').textContent, todoList);
      expect(expectInputContent).to.eql('homework');
    }) 
	//渲染todolist,获取所有任务
    it('should get all todo', async function(){
      let todoList1 = await page.$eval('#todo-list', el=> el.innerText);
      expect(todoList1).to.eql('homework\nnew todo item');
    })

    //删除homework
    it('should delete correct todo', async function(){
      const element = await page.$('.view');
      const size = await element.boundingBox();
      await page.mouse.move(size.x, size.y);
      await page.click('.destroy', {delay:500}); 
      await page.waitFor(500);
      let todoList2 = await page.$eval('#todo-list', el=> el.innerText);
      expect(todoList2).to.eql('new todo item');
    })
	//选择new todo item已完成，并在completed里面查看
    it('should set todo checked', async function(){
      await page.click('.toggle', {delay:500});
      await page.click('.selected');
      let todoList3 = await page.$eval('#todo-list', el=> el.innerText);
      expect(todoList3).to.eql('new todo item');
    })
  });
