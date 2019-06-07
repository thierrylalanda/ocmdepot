/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function ()
{
   
    function after_form_submitted(data, form)
    {
        if (data.result === 'success')
        {
            // location.reload();
            form = $(this);
            //show some response on the button
            $('button[type="submit"]', form).each(function ()
            {
                $btn = $(this);
                $btn.prop('type', 'button');
                $btn.prop('orig_label', $btn.text());
                $btn.text('Enregistrer');
            });
        } else
        {



        }//else
    }

    $('#reused_form').submit(function (e)
    {
        e.preventDefault();

        $form = $(this);
        //show some response on the button
        $('button[type="submit"]', $form).each(function ()
        {
            $btn = $(this);
            $btn.prop('type', 'button');
            $btn.prop('orig_label', $btn.text());
            $btn.text('Sending ...');
        });


        var formdata = new FormData(this);
        $.ajax({
            type: "POST",
            url: 'handler.php',
            data: formdata,
            success: after_form_submitted,
            dataType: 'json',
            processData: false,
            contentType: false,
            cache: false
        });

    });
    /**
     * @description ajout de la societe
     * @param {type} e description
     */
    $('#addSociete').submit(function (e)
    {
        e.preventDefault();
       // Manage.Toast("error", "Alert", "Erreur de sauvegarde");
        $form = $(this);
        //show some response on the button
        $('button[type="submit"]', $form).each(function ()
        {
            $btn = $(this);
            $btn.prop('type', 'button');
            $btn.prop('orig_label', $btn.text());
            $btn.text('Sending ...');
        });


        var formdata = new FormData(this);
        $.ajax({
            type: "POST",
            url: $form.attr("action"),
            data: formdata,
            success: function (data) {
                after_form_submitted(data, $form);
            },
            dataType: 'json',
            processData: false,
            contentType: false,
            cache: false
        });

    });
});