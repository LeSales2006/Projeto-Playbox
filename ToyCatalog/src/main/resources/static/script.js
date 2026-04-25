function mostrar(secao){
  document.querySelectorAll('.conteudo').forEach(c=>c.classList.remove('ativo'))
  document.getElementById(secao).classList.add('ativo')
}

/* =========================
   DADOS
========================= */
let dados = []
let editIndex = null
let deleteIndex = null

/* =========================
   RENDER (TABELA + HOME)
========================= */
function render(){
  const tbody = document.getElementById('tbody')
  tbody.innerHTML = ''

  const hq = document.getElementById('home-queridinhos')
  const hc = document.getElementById('home-carrinhos')
  const hb = document.getElementById('home-bonecas')
  const ho = document.getElementById('home-outros')

  // limpar home
  hq.innerHTML = ''
  hc.innerHTML = ''
  hb.innerHTML = ''
  ho.innerHTML = ''

  dados.forEach((item,i)=>{

    /* ===== TABELA ===== */
    tbody.innerHTML += `
    <tr>
      <td>${item.desc}</td>
      <td>${item.cat}</td>
      <td>${item.valor}</td>
      <td>
        <a onclick="editar(${i})">Editar</a> -
        <a onclick="abrirModal(${i})">Excluir</a>
      </td>
    </tr>`

    /* ===== CARD COM IMAGEM ===== */
    let card = `
      <div class="card">
        <img src="${item.img}" style="width:100%;height:80px;object-fit:contain;">
        <p>${item.desc}</p>
        <small>${item.valor}</small>
      </div>
    `

    /* ===== HOME ===== */
    if(item.cat === "Queridinhos da Molecada"){ hq.innerHTML += card }
    if(item.cat === "Carrinhos"){ hc.innerHTML += card }
    if(item.cat === "Bonecas"){ hb.innerHTML += card }
    if(item.cat === "Outros"){ ho.innerHTML += card }

  })
}

/* =========================
   SALVAR COM IMAGEM
========================= */
function salvar(){

  const file = document.getElementById('imgInput').files[0]

  if(!file){
    alert("Selecione uma imagem!")
    return
  }

  const reader = new FileReader()

  reader.onload = function(e){

    dados.push({
      desc: desc.value,
      cat: cat.value,
      valor: valor.value,
      det: det.value,
      img: e.target.result
    })

    limparFormulario()

    render()
    mostrar('home')
  }

  reader.readAsDataURL(file)
}

/* =========================
   EDITAR
========================= */
function editar(i){
  editIndex = i
  let d = dados[i]

  editDesc.value = d.desc
  editCat.value = d.cat
  editValor.value = d.valor
  editDet.value = d.det

  mostrar('editar')
}

/* =========================
   ATUALIZAR
========================= */
function atualizar(){
  dados[editIndex] = {
    ...dados[editIndex],
    desc: editDesc.value,
    cat: editCat.value,
    valor: editValor.value,
    det: editDet.value
  }

  render()
  mostrar('home')
}

/* =========================
   MODAL
========================= */
function abrirModal(i){
  deleteIndex = i
  document.getElementById('modal').style.display = 'flex'
}

function fecharModal(){
  document.getElementById('modal').style.display = 'none'
}

function confirmar(){
  dados.splice(deleteIndex,1)
  fecharModal()
  render()
}

/* =========================
   LIMPAR FORM
========================= */
function limparFormulario(){
  desc.value = ''
  valor.value = ''
  det.value = ''
  imgInput.value = ''
}

/* =========================
   FORMATAR PREÇO
========================= */
function formatarPreco(valor) {
    if (!valor && valor !== 0) return 'R$0,00';
    return valor.toLocaleString('pt-br', {
        style: 'currency',
        currency: 'BRL'
    });
}

/* =========================
   NOVA CATEGORIA (BOTÃO MAIS)
========================= */
function criarCategoria(){
  const nome = prompt("Digite o nome da nova categoria:")

  if(!nome || nome.trim() === '') return

  // SELECT NOVO
  const select = document.getElementById('cat')
  const option = document.createElement('option')
  option.text = nome
  select.add(option)

  // SELECT EDITAR
  const selectEdit = document.getElementById('editCat')
  const option2 = document.createElement('option')
  option2.text = nome
  selectEdit.add(option2)

  // CRIAR BOTÃO NO CATÁLOGO
  const container = document.querySelector('.categorias')

  const nova = document.createElement('div')
  nova.className = 'categoria'
  nova.innerHTML = `
    <img src="img/mais.png">
    <p>${nome}</p>
  `

  nova.onclick = () => filtrarCategoria(nome)

  container.appendChild(nova)
}

