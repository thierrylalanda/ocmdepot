/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { commands }  from "./printerCommande.js";

//console.log(commands);
function createCommandePdfMiniPrint(LCommande) {
    // u can remove this when generate the receipt using another method
    let receipt = '';
    if (this.societe.nom) {
      
      this.buff.write(commands.HARDWARE.HW_INIT);
      this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
      this.buff.write(commands.TEXT_FORMAT.TXT_BOLD_ON);
      this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_CT);
      this.buff.write(this.societe.nom);
      this.buff.write(commands.EOL);
    }
    this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
    this.buff.write(commands.TEXT_FORMAT.TXT_BOLD_ON);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_LT);
    this.buff.write('commande: '+LCommande.id);
    this.buff.write(commands.EOL);
    this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
    this.buff.write(commands.TEXT_FORMAT.TXT_BOLD_ON);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_LT);
    this.buff.write('client: ' +LCommande.client.nom);
    this.buff.write(commands.EOL);
    this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_LT);
    this.buff.write('date: '+ LCommande.datecc);
    this.buff.write(commands.EOL);
    this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_LT);
    this.buff.write('livraison: '+LCommande.dateliv);
    this.buff.write(commands.EOL);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_LT);
    this.buff.write('Statut : '+LCommande.etatc.nom);
      
    //tableau des produits
    let total=0;
    let totalP=0;
    this.buff.write(commands.EOL+commands.EOL);
    this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_LT);
    this.buff.write(commands.TEXT_FORMAT.TXT_BOLD_ON);
    this.buff.write('Article \t');
    this.buff.write(commands.LINE_SPACING.LS_SET);
    this.buff.writeUInt8(4);
    this.buff.write(commands.TEXT_FORMAT.TXT_BOLD_OFF);
    this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_CT);
    this.buff.write(commands.TEXT_FORMAT.TXT_BOLD_ON);
    this.buff.write('Quantite \t');
    this.buff.write(commands.LINE_SPACING.LS_SET);
    this.buff.writeUInt8(4);
    this.buff.write(commands.TEXT_FORMAT.TXT_BOLD_OFF);
    this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_RT);
    this.buff.write(commands.TEXT_FORMAT.TXT_BOLD_ON);
    this.buff.write('Prix ');
    this.buff.write(commands.TEXT_FORMAT.TXT_BOLD_OFF);
    this.buff.write(commands.EOL);
    if(LCommande.tcommandesList) {
      for (let i = 0; i < LCommande.tcommandesList.length; i++) {

        let item = LCommande.tcommandesList[i];
        this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_LT);
    this.buff.write(item.article.code+'\t');
    this.buff.write(commands.LINE_SPACING.LS_SET);
	this.buff.writeUInt8(4);
    this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_CT);
    this.buff.write(this.formatNumber(item.quantite)+'\t');
    this.buff.write(commands.LINE_SPACING.LS_SET);
	this.buff.writeUInt8(4);
    this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_RT);
    this.buff.write(this.formatNumber(item.prixTotal));
    this.buff.write(commands.EOL);
	this.buff.write(commands.EOL);
        total += item.prixTotal;
        totalP += item.quantite;
      }
    }
   
//pied de page 
this.buff.write(commands.EOL);
this.buff.write(commands.HORIZONTAL_LINE.HR_58MM);
this.buff.write(commands.EOL);
this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_LT);
this.buff.write(commands.TEXT_FORMAT.TXT_BOLD_ON);
    this.buff.write('Qte Produit: '+this.formatNumber(totalP));
    this.buff.write(commands.EOL);
    this.buff.write(commands.TEXT_FORMAT.TXT_NORMAL);
    this.buff.write(commands.TEXT_FORMAT.TXT_ALIGN_LT);
    this.buff.write(commands.TEXT_FORMAT.TXT_BOLD_ON);
    this.buff.write('Total: '+this.formatNumber(total));
    this.buff.write(commands.EOL);
    this.buff.write(commands.HORIZONTAL_LINE.HR_58MM);
    this.buff.write(commands.EOL);
    //code bar 
    /* const obj= {
      id:btoa(LCommande.id),
      type:'commande'
    };
    const qr =JSON.stringify(obj);
    this.buff.write(commands.CODE2D_FORMAT.CODE2D);
    this.buff.writeUInt8(3);
    this.buff.writeUInt8(3);
    this.buff.writeUInt8(8);
    this.buff.writeUInt16LE(qr.length);
    this.buff.write(qr); */
    //secure space on footer
    this.buff.write(commands.EOL);
    this.buff.write(commands.EOL);
    this.buff.write(commands.EOL);
    this.buff.write(commands.EOL);
    this.buff.write(commands.PAPER.PAPER_FULL_CUT);
    //this.buff.write(commands.HARDWARE.HW_INIT);
    this.buff.flush();
  };
