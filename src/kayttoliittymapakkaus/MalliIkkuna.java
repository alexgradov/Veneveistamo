/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Malli;
import java.util.Arrays;
import tietovarastopakkaus.MalliTietovarasto;

/**
 *
 * @author s1300778
 */
public final class MalliIkkuna extends Ikkuna {

    private final MalliTietovarasto rekisteri = new MalliTietovarasto();

    public MalliIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        int masto = Integer.parseInt(arvot[2]);
        rekisteri.muutaTietoja(new Malli(id, arvot[1], masto));
        paivitaValintaLista();

    }

    @Override
    public void suoritaPoisto() {
        int id = (int) taulukkoMalli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaTieto(id);
        paivitaValintaLista();
    }

    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            int masto = Integer.parseInt(arvot[2]);
            rekisteri.lisaaTieto(new Malli(id, arvot[1], masto));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n ja Maston:n pitää olla kokonaislukuja");
        }
    }

    @Override
    public void haeKaikkiTiedot() {
        for (Malli malli : rekisteri.haeTiedot()) {
            taulukkoMalli.addRow(Arrays.asList(malli.getId(), malli.getMalli(), malli.getMasto()));
        }
    }

}