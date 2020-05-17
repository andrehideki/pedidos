import { Component } from '@angular/core';
import { FormBuilder, FormArray, ValidatorFn, AbstractControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  	title = 'pedidos-ui';
	incluirProduto = false;
	// osifjiosdafja

	formulario = this.fb.group({
		fornecedor: ['', Validators.required ],
		nome: ['', Validators.required ],
		qtde: ['', [ Validators.required, this.onlyNumbers() ] ],
		produtos: this.fb.array([])
	});

	constructor(private fb: FormBuilder) {
	}

	get produtos() {
		return this.formulario.get('produtos') as FormArray;
	}

	incluir() {
		const nome = this.formulario.get('nome').value;
		const qtde = this.formulario.get('qtde').value;
		this.produtos.push(this.fb.group({
			nome: nome,
			qtde: qtde
		}));
	}

	onSubmit() {
		console.log(this.formulario);
	}

	onlyNumbers(): ValidatorFn {
		return (control: AbstractControl): any  => {
			return /\d+$/.test(control.value) ? null : { value: 'Only numbers'};	
		};		
	}
}
