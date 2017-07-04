/**
 * Created by Miguel on 04/05/2017.
 */
var clientes = [];
function diminuirTres(event) {
    var format = moment.utc(event).format();
    format = format.toString();

    var date = format.substr(11, 13);
    if (format == 'Invalid date') {
        date = 0;
    } else {
        date = parseInt(date) - 3;
    }
    console.log(date)
    if (date < 0) {
        date = date + 24;
    }
    if (date < 10) {
        date = "0" + date.toString();
    }

    console.log(date)
    format = format.substr(0, 11) + date + format.substr(13, format.length);
    console.log(format)

    return format;
}

function formatarUTC(event) {
    var format = moment.utc(event).format();
    var format = format.toString();
    format = diminuirTres(format);
    format = format.substr(0, (format.length - 1));
    return format;
}

$('#modalCalendar').hide();
$('#modalFuncionario').hide();
$('#modalCliente').hide();
$(document).ready(function () {
    $.datetimepicker.setLocale('pt-BR');
    $('#calendar').fullCalendar({
        customButtons: {
            novo: {
                text: 'Nova Reserva',
                click: function () {
                    $('#modalCalendar').show();
                }
            }
        },
        header: {
            left: 'prev,next today novo',
            center: 'month,listWeek,listDay ',
            right: 'title'
        },

        // customize the button names,
        // otherwise they'd all just say "list"
        views: {
            listDay: {buttonText: 'Dia'},
            listWeek: {buttonText: 'Semana'},
        },
        handleWindowResize: false,
        defaultView: 'month',
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        events: '/json',
        timezone: 'local',
        eventDrop: function (event, delta, revertFunc) {
            if (!confirm("Deseja mudar?")) {
                revertFunc();
            } else {
                jsRoutes.controllers.CalendarioController.update().ajax({
                    data: JSON.stringify([{
                        'id': event.id,
                        'title': event.title,
                        'start': diminuirTres(event.start),
                        'end': diminuirTres(event.end),
                        'color': event.color
                    }]),
                    contentType: 'application/json'
                });
            }

        },

        eventClick: function (event, jsEvent, view) {

            $('#title').val(event.title);
            //console.log(formatarUTC(event.start));
            var start = formatarUTC(event.start),
                end = formatarUTC(event.end);

            $('#start').val(start);
            $('#end').val(end);
            $('#modalCalendar').show();

        }

    });
    $('#calendar').find('.fc-view-container').addClass('col-sm');
    $('#btn-close').on('click', function () {
        $('#modalCalendar').hide();
    });

    $('#modalCalendar .btn-save').on('click', function (e) {
        e.preventDefault();
        jsRoutes.controllers.CalendarioController.save().ajax({
            data: JSON.stringify([{
                'title': $('#title').val(),
                'start': $('#start').val(),
                'end': $('#end').val(),
                'color': $('#color').val()
            }]),
            contentType: 'application/json',
            success: function () {
                $('#calendar').fullCalendar('removeEvents');
                $('#calendar').fullCalendar('addEventSource', '/json');
                $('#calendar').fullCalendar('rerenderEvents');
            }
        });
        $('#modalCalendar').hide();

    });

    $('li.dropdown a.dropdown-toggle').on('click', function () {
        // $('li.dropdown ul').toggleClass('active');
        $(this).addClass('active');
        $(this).next().slideToggle();
    });

    $('#modalFuncionarioToggle').on('click', function (e) {
        e.preventDefault();
        $('#modalFuncionario').show();
    });

    $('#modalFuncionario .btn-close').on('click', function () {
        $('#modalFuncionario').hide();
    });

    $('#modalClienteToggle').on('click', function (e) {
        e.preventDefault();
        $('#modalCliente').show();
    });

    $('#modalCliente .btn-close').on('click', function () {
        $('#modalCliente').hide();
    });


    // Carregar através do cep
    $('input[name="cep"]').blur(function (e) {
        var cep = $('input[name="cep"]').val() || '';
        if (!cep) {
            return
        }

        var url = 'http://viacep.com.br/ws/' + cep + '/json';
        $.getJSON(url, function (data) {
            if ("error" in data) {
                return
            }

            $('input[name="endereco"]').val(data.logradouro);
            $('input[name="bairro"]').val(data.bairro);
            $('input[name="cidade"]').select(data.localidade);

        });

    });


    $('#start').datetimepicker({
        format: 'd/m/Y H:i',
        onChangeDateTime: function (dp, $input) {
            $('#end').val($input.val());
        },
        allowTimes: [
            '08:00', '09:00', '10:00',
            '11:00', '12:00', '13:00',
            '14:00', '15:00', '16:00',
            '17:00', '18:00', '19:00',
            '20:00', '21:00'
        ],

    });
    $('#end').datetimepicker({
        format: 'd/m/Y H:i',
        allowTimes: [
            '08:00', '09:00', '10:00',
            '11:00', '12:00', '13:00',
            '14:00', '15:00', '16:00',
            '17:00', '18:00', '19:00',
            '20:00', '21:00'
        ],
        locale: 'pt-br'
    });


    $('.btnFuncEdit').on('click', function (e) {
        jsRoutes.controllers.FuncionarioController.edit($(this).attr('data-id')).ajax({
            contentType: 'application/json',
            success: function (data) {
                $('#modalFuncionario #nome').val(data.usuario_id.nome);
                $('#modalFuncionario #email').val(data.usuario_id.email);
                $('#modalFuncionario #cpf').val(data.usuario_id.cpf);
                $('#modalFuncionario #cep').val(data.usuario_id.endereco_id.cep);
                $('#modalFuncionario #endereco').val(data.usuario_id.endereco_id.rua);
                $('#modalFuncionario #bairro').val(data.usuario_id.endereco_id.bairro);
                $('#modalFuncionario #numero').val(data.usuario_id.endereco_id.numero);
                $('#modalFuncionario #complemento').val(data.usuario_id.endereco_id.complemento);
            }
        });
        $('#modalFuncionario').show();
    });

    $('.btnCliEdit').on('click', function (e) {
        jsRoutes.controllers.ClienteController.edit($(this).attr('data-id')).ajax({
            contentType: 'application/json',
            success: function (data) {
                $('#modalCliente #nome').val(data.usuario_id.nome);
                $('#modalCliente #email').val(data.usuario_id.email);
                $('#modalCliente #cpf').val(data.usuario_id.cpf);
                $('#modalCliente #cep').val(data.usuario_id.endereco_id.cep);
                $('#modalCliente #endereco').val(data.usuario_id.endereco_id.rua);
                $('#modalCliente #bairro').val(data.usuario_id.endereco_id.bairro);
                $('#modalCliente #numero').val(data.usuario_id.endereco_id.numero);
                $('#modalCliente #complemento').val(data.usuario_id.endereco_id.complemento);
            }
        });
        $('#modalCliente').show();

    });
    jsRoutes.controllers.ClienteController.indexJson().ajax({
        contentType: 'application/json',
        success: function (data) {
            var cl;
            data.forEach(function (value) {
                clientes.push(value.usuario_id.nome);
            })

        }
    });
    $('#modalCalendar #title').on('focus', function () {
        $('#modalCalendar #title').autocomplete({
            source: clientes
        });
    })
});
