#ifndef kld_H
#define kld_H

#include <Classes.hpp>
#include <Controls.hpp>
#include <StdCtrls.hpp>
#include <Forms.hpp>
#include <ComCtrls.hpp>
#include <ExtCtrls.hpp>

class TForm1 : public TForm
{
__published:	// IDE-managed Components
    TUpDown *UpDown1;
    void __fastcall FormPaint(TObject *Sender);
    void __fastcall UpDown1Click(TObject *Sender, TUDBtnType Button);
    void __fastcall FormKeyDown(TObject *Sender, WORD &Key,
          TShiftState Shift);
private:
    Graphics::TBitmap *backimage; // ������� �������
public:
    __fastcall TForm1(TComponent* Owner);

protected:
    /* C������ ���������� ��������� WM_NCHITTEST ���� ����������,
       ����� ������������ ������ � � ������ �������/���������� ������ ����.
       ������� WMNCHITTEST ������������ ��� ���������.
    */
    void __fastcall WMNCHITTEST(TMessage &Message);
    BEGIN_MESSAGE_MAP
         MESSAGE_HANDLER(WM_NCHITTEST, TMessage, WMNCHITTEST)
    END_MESSAGE_MAP(TControl)

};

extern PACKAGE TForm1 *Form1;

#endif
